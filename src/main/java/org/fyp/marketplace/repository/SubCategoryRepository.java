package org.fyp.marketplace.repository;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubCategoryRepository extends MongoRepository<SubCategory,Integer> {
    SubCategory findBy_id(ObjectId _id);
    SubCategory findByCategoryId(ObjectId categoryId);
}
