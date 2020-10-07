package org.fyp.marketplace.controller;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.SubCategory;
import org.fyp.marketplace.service.SubCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/subCategory")
public class SubCategoryController {
    final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        super();
        this.subCategoryService = subCategoryService;
    }



    @GetMapping("/all")
    public List<SubCategory> listAllSubCategory() {
        try {
            return this.subCategoryService.getAllSubCategory();
        } catch (Exception e) {
            // Log error
            return new ArrayList<SubCategory>();
        }
    }

    @GetMapping("/{subCategoryId}")
    public SubCategory getSubCategory(@PathVariable ObjectId subCategoryId) {

        SubCategory subCategory = subCategoryService.getSubCategoryById(subCategoryId);

        // throw exception if null

        if (subCategory == null) {
            throw new RuntimeException("SubCategory not found");
        }

        return subCategory;
    }

    @GetMapping("/{categoryId}")
    public SubCategory getSubCategoryByCategoryId(@PathVariable ObjectId categoryId) {

        SubCategory subCategory = subCategoryService.getSubCategoryByCategoryId(categoryId);

        // throw exception if null

        if (subCategory == null) {
            throw new RuntimeException("SubCategory not found");
        }

        return subCategory;
    }

    @PostMapping("/add")
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory) throws Exception {
        ResponseEntity<SubCategory> result;
        try {
            this.subCategoryService.addSubCategory(subCategory);
            result = new ResponseEntity<>(subCategory, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(subCategory, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/update")
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategory subCategory) throws Exception {
        ResponseEntity<SubCategory> result;
        try {
            this.subCategoryService.updateSubCategory(subCategory);
            result = new ResponseEntity<>(subCategory, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(subCategory, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/delete/{subCategoryId}")
    public String deleteSubCategory(@PathVariable ObjectId subCategoryId) {

        SubCategory subCategory = subCategoryService.getSubCategoryById(subCategoryId);

        // throw exception if null

        if (subCategory == null) {
            throw new RuntimeException("Sub Category not found");
        }

        subCategoryService.deleteSubCategory(subCategory);

        return "Deleted Sub Category : " + subCategory.getSubCategoryName();
    }
}
