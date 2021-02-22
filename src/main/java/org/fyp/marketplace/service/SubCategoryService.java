package org.fyp.marketplace.service;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.SubCategory;
import org.fyp.marketplace.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepository subCategoryRepository;

    public List<SubCategory> getAllSubCategory() {
        return this.subCategoryRepository.findAll();
    }

    public SubCategory getSubCategoryById(long _id) {
        return this.subCategoryRepository.findById(_id).get();
    }

    public List<SubCategory> getSubCategoryByCategoryId(long categoryId){
        return subCategoryRepository.findByCategoryId(categoryId);
    }

    public SubCategory addSubCategory(SubCategory subCategory) {
    	subCategory.setInsertDate(new Date());
    	subCategory.setLastUpdateDate(new Date());
        SubCategory newSubCategory = this.subCategoryRepository.save(subCategory);
        return newSubCategory;
    }

    public SubCategory updateSubCategory(SubCategory subCategory) {
        subCategory.setLastUpdateDate(new Date());
        return this.subCategoryRepository.save(subCategory);
    }

    public void deleteSubCategory(SubCategory subCategory) {
        this.subCategoryRepository.delete(subCategory);
    }
}
