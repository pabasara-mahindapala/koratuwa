package lk.agrohub.market.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lk.agrohub.market.dtos.ProductDto;
import lk.agrohub.market.dtos.UserDto;
import lk.agrohub.market.model.Category;
import lk.agrohub.market.model.ERole;
import lk.agrohub.market.model.ImageModel;
import lk.agrohub.market.model.Product;
import lk.agrohub.market.model.Role;
import lk.agrohub.market.model.User;
import lk.agrohub.market.repository.ImageRepository;
import lk.agrohub.market.repository.RoleRepository;
import lk.agrohub.market.repository.UserRepository;
import lk.agrohub.market.security.jwt.JwtUtils;
import lk.agrohub.market.security.request.LoginRequest;
import lk.agrohub.market.security.request.SignupRequest;
import lk.agrohub.market.security.request.UpdateRequest;
import lk.agrohub.market.security.response.JwtResponse;
import lk.agrohub.market.security.response.MessageResponse;
import lk.agrohub.market.service.UserDetailsImpl;
import lk.agrohub.market.util.FileUploadUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ImageRepository imageRepository;

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
			Role transporterRole = roleRepository.findByName(ERole.ROLE_TRANSPORTER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(transporterRole);
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
				case "transporter":
					Role transporterRole = roleRepository.findByName(ERole.ROLE_TRANSPORTER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(transporterRole);

					break;
				default:
					Role buyerRoleNew = roleRepository.findByName(ERole.ROLE_BUYER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(buyerRoleNew);
					Role sellerRoleNew = roleRepository.findByName(ERole.ROLE_SELLER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(sellerRoleNew);
					Role transporterRoleNew = roleRepository.findByName(ERole.ROLE_TRANSPORTER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(transporterRoleNew);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/all")
	public List<UserDto> listAllUsers() {
		try {
			List<User> users = userRepository.findAll();

			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User u : users) {
				userDtos.add(new UserDto(u, imageRepository.findByUserId(u.getId()) == null ? null
						: imageRepository.findByUserId(u.getId()).getUrl()));
			}
			return userDtos;
		} catch (Exception e) {
			// Log error
			return new ArrayList<UserDto>();
		}
	}

	@GetMapping("/{userId}")
	public UserDto getUser(@PathVariable long userId) {

		Optional<User> user = userRepository.findById(userId);

		// throw exception if null
		if (!user.isPresent()) {
			throw new RuntimeException("User not found");
		}

		return new UserDto(user.get(),
				imageRepository.findByUserId(userId) == null ? null : imageRepository.findByUserId(userId).getUrl());
	}

	@PostMapping("/logout")
	@PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
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
	public String deleteUser(@PathVariable long userId) {
		Optional<User> user = userRepository.findById(userId);

		// throw exception if null
		if (!user.isPresent()) {
			throw new RuntimeException("User not found");
		}

		userRepository.delete(user.get());

		return "Deleted user : " + user.get().getUsername();
	}

	@PostMapping("/images/add")
	public String addImage(@RequestParam("image") MultipartFile multipartFile, @RequestParam Long userId)
			throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		String uploadDir = "images/user-images/" + userId;

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		ImageModel image = new ImageModel(1, userId, null, fileName, multipartFile.getContentType(), new Date(),
				uploadDir + "/" + fileName);
		this.imageRepository.save(image);

		return "Added image : " + fileName;
	}
}