package org.fyp.marketplace.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubCategoryRepository extends MongoRepository<SubCategory,Long> {
//    SubCategory findBy_id(ObjectId _id);
	List<SubCategory> findByCategoryId(long categoryId);
}
