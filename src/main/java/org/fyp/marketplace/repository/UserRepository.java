package org.fyp.marketplace.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);

	User findBy_id(ObjectId _id);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}