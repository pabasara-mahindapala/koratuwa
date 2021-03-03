package org.fyp.marketplace.repository;

import org.fyp.marketplace.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review,Long> {
//	Review findBy_id(ObjectId _id);
}
