///**
// * 
// */
//package lk.agrohub.market.repository;
//
//import java.util.List;
//
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import lk.agrohub.market.customrepository.StockCustomRepository;
//import lk.agrohub.market.model.Stock;
//
//public interface StockRepository extends MongoRepository<Stock, Long>, StockCustomRepository {
//	Stock findByProductId(long productId);	
//}
