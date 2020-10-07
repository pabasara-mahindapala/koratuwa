package org.fyp.marketplace.service;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Category;
import org.fyp.marketplace.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(ObjectId _id) {
        return this.categoryRepository.findBy_id(_id);
    }

    public Category addCategory(Category category) {
        Category newCategory = this.categoryRepository.save(category);
        return newCategory;
    }

    public Category updateCategory(Category category) {
        category.setLastUpdateDate(new Date());
        return this.categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }
}
