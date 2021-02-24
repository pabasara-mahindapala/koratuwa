package org.fyp.marketplace.repository;

import org.fyp.marketplace.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,Long> {
//    Category findBy_id(ObjectId _id);
}
