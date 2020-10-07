package org.fyp.marketplace.repository;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,Integer> {
    Product findBy_id(ObjectId _id);
    Product findByCategoryId(ObjectId categoryId);
    Product findBySubCategoryId(ObjectId subCategoryId);
    Product findByProducerId(ObjectId producerId);
}
