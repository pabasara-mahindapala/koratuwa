package lk.agrohub.market.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import lk.agrohub.market.model.CartItem;
import lk.agrohub.market.model.ImageModel;

public interface ImageRepository extends MongoRepository<ImageModel, Long> {
	ImageModel findByUserId(long userId);
	
	List<ImageModel> findByStockId(long stockId);
}
