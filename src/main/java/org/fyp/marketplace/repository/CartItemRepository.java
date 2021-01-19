package org.fyp.marketplace.repository;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.CartItem;
import org.fyp.marketplace.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartItemRepository extends MongoRepository<CartItem, Integer> {

    CartItem findByCustomerId(ObjectId cartItemId);
}
