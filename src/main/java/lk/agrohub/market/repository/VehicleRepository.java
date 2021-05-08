package lk.agrohub.market.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import lk.agrohub.market.model.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, Long> {
//    Vehicle findBy_id(ObjectId _id);
}
