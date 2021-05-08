/**
 * 
 */
package lk.agrohub.market.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import lk.agrohub.market.model.Order;

/**
 * @author OmPrakashP
 *
 */
public interface OrderRepository extends MongoRepository<Order, Long> {

	List<Order> findByProductId(long productId);

}
