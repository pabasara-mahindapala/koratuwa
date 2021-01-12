package org.fyp.marketplace.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.ERole;
import org.fyp.marketplace.model.Review;
import org.fyp.marketplace.model.Role;
import org.fyp.marketplace.model.User;
import org.fyp.marketplace.repository.ReviewRepository;
import org.fyp.marketplace.repository.RoleRepository;
import org.fyp.marketplace.repository.UserRepository;
import org.fyp.marketplace.security.jwt.JwtUtils;
import org.fyp.marketplace.security.request.LoginRequest;
import org.fyp.marketplace.security.request.SignupRequest;
import org.fyp.marketplace.security.request.UpdateRequest;
import org.fyp.marketplace.security.response.JwtResponse;
import org.fyp.marketplace.security.response.MessageResponse;
import org.fyp.marketplace.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName(),
				new Date(), new Date(), signUpRequest.getMobileNumber(), signUpRequest.getAddress());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(buyerRole);
			Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(sellerRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "buyer":
					Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(buyerRole);

					break;
				case "seller":
					Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(sellerRole);

					break;
				default:
					Role buyerRoleNew = roleRepository.findByName(ERole.ROLE_BUYER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(buyerRoleNew);
					Role sellerRoleNew = roleRepository.findByName(ERole.ROLE_SELLER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(sellerRoleNew);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/logout")
	@PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN')")
	public ResponseEntity<?> logoutUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(null);
		return ResponseEntity.ok(new MessageResponse("Logged out successfuly"));
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateRequest updateRequest) {
		if (userRepository.existsByEmail(updateRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		Optional<User> optionalUser = userRepository.findByUsername(updateRequest.getUsername());

		if (optionalUser.isPresent()) {
			User oldUser = optionalUser.get();
			if (updateRequest.getEmail() != null) {
				oldUser.setEmail(updateRequest.getEmail());
			}
			if (updateRequest.getFirstName() != null) {
				oldUser.setFirstName(updateRequest.getFirstName());
			}
			if (updateRequest.getLastName() != null) {
				oldUser.setLastName(updateRequest.getLastName());
			}
			if (updateRequest.getMobileNumber() != null) {
				oldUser.setMobileNumber(updateRequest.getMobileNumber());
			}
			if (updateRequest.getAddress() != null) {
				oldUser.setAddress(updateRequest.getAddress());
			}

			oldUser.setLastUpdateDate(new Date());

			Set<String> strRoles = updateRequest.getRoles();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(buyerRole);
				Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(sellerRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "buyer":
						Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(buyerRole);

						break;
					case "seller":
						Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(sellerRole);

						break;
					default:
						Role buyerRoleNew = roleRepository.findByName(ERole.ROLE_BUYER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(buyerRoleNew);
						Role sellerRoleNew = roleRepository.findByName(ERole.ROLE_SELLER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(sellerRoleNew);
					}
				});
			}

			oldUser.setRoles(roles);
			userRepository.save(oldUser);

			return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@PathVariable ObjectId userId) {
		User user = userRepository.findBy_id(userId);

        // throw exception if null
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        userRepository.delete(user);

        return "Deleted user : " + user.getUsername();
	}
	

	
	@PostMapping("/review/{userId}")
	@PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<Review> reviewUser(@PathVariable ObjectId userId, @RequestBody Review review) throws Exception {
        ResponseEntity<Review> result;
        try {
        	String currentUserName;
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		if (!(authentication instanceof AnonymousAuthenticationToken)) {
    		    currentUserName = authentication.getName();
    		    Optional<User> reviewer = userRepository.findByUsername(currentUserName);
    		    review.setReviewerId(reviewer.get().get_id());
    		    review.setInsertDate(new Date());
    		    Review addedReview = reviewRepository.save(review);
    		    User reviewedUser = userRepository.findBy_id(userId);
    		    Set<Review> reviews = reviewedUser.getReviews();
    		    if (reviews == null) {
					reviews = new HashSet<>();
				}
    		    reviews.add(addedReview);
    		    reviewedUser.setReviews(reviews);
    		    userRepository.save(reviewedUser);
    		    result = new ResponseEntity<>(review, HttpStatus.OK);
    		} else {
    			result = new ResponseEntity<>(review, HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		
        } catch (Exception e) {
            result = new ResponseEntity<>(review, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}