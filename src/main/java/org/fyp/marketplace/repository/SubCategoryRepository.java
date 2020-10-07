package org.fyp.marketplace.repository;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubCategoryRepository extends MongoRepository<SubCategory,Integer> {
    SubCategory findBy_id(ObjectId _id);
    SubCategory findByCategoryId(ObjectId categoryId);
}
