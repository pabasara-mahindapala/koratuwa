package org.fyp.marketplace.repository;

import org.fyp.marketplace.model.CartItem;
import org.fyp.marketplace.model.ImageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageModel, Long> {
	ImageModel findByUserId(long userId);
}
