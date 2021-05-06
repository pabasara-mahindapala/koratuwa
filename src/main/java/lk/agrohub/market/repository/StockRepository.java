/**
 * 
 */
package org.fyp.marketplace.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.fyp.marketplace.customrepository.StockCustomRepository;
import org.fyp.marketplace.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, Long>, StockCustomRepository {
	Stock findByProductId(long productId);	
}
