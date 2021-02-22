package org.fyp.marketplace.repository;

import org.fyp.marketplace.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, Long> {
//    Vehicle findBy_id(ObjectId _id);
}
