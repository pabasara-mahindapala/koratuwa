package lk.agrohub.market.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import lk.agrohub.market.model.CartItem;
import lk.agrohub.market.model.Product;

import java.util.List;

public interface CartItemRepository extends MongoRepository<CartItem, Long> {

    CartItem findByCustomerId(long cartItemId);
}
