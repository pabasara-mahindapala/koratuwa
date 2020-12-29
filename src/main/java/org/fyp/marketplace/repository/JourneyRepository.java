package org.fyp.marketplace.repository;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Journey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JourneyRepository extends MongoRepository<Journey, Integer> {

    List<Journey> findByJourneyId(ObjectId journeyId);
    Journey findBy_id(ObjectId _id);
}
