package org.fyp.marketplace.repository;

import java.util.List;

import org.fyp.marketplace.customrepository.ProductCustomRepository;
import org.fyp.marketplace.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,Long>, ProductCustomRepository {
//    Product findBy_id(ObjectId _id);
    List<Product> findByCategoryId(long categoryId);
    List<Product> findBySubCategoryId(long subCategoryId);
    List<Product> findByProducerId(long producerId);
}
