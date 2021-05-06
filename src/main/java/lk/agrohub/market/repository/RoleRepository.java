package org.fyp.marketplace.repository;

import java.util.Optional;

import org.fyp.marketplace.model.ERole;
import org.fyp.marketplace.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(ERole name);
	}
