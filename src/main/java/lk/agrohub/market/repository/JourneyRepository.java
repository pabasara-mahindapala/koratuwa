package lk.agrohub.market.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import lk.agrohub.market.model.Journey;

import java.util.List;

public interface JourneyRepository extends MongoRepository<Journey, Long> {

//    List<Journey> findByJourneyId(ObjectId journeyId);
//    Journey findBy_id(ObjectId _id);
}
