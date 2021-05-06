package org.fyp.marketplace.controller;

import java.util.ArrayList;
import java.util.List;

import org.fyp.marketplace.model.SubCategory;
import org.fyp.marketplace.service.SubCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/subCategory")
public class SubCategoryController {
    final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        super();
        this.subCategoryService = subCategoryService;
    }



    @GetMapping("/all")
    public List<SubCategory> listAllSubCategory(@RequestParam(required = false) Long categoryId) {
        try {
        	if (categoryId != null) {
        		return subCategoryService.getSubCategoryByCategoryId(categoryId);
			} else {
				return this.subCategoryService.getAllSubCategory();	
			}
        } catch (Exception e) {
            // Log error
            return new ArrayList<SubCategory>();
        }
    }

    @GetMapping("/{subCategoryId}")
    public SubCategory getSubCategory(@PathVariable long subCategoryId) {

        SubCategory subCategory = subCategoryService.getSubCategoryById(subCategoryId);

        // throw exception if null

        if (subCategory == null) {
            throw new RuntimeException("SubCategory not found");
        }

        return subCategory;
    }

//    @GetMapping("/all")
//    public List<SubCategory> getSubCategoryByCategoryId(@RequestParam long categoryId) {
//
//    	try {
//            return subCategoryService.getSubCategoryByCategoryId(categoryId);
//        } catch (Exception e) {
//            // Log error
//            return new ArrayList<SubCategory>();
//        }
//    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSubCategory(@PathVariable long subCategoryId) {

        SubCategory subCategory = subCategoryService.getSubCategoryById(subCategoryId);

        // throw exception if null

        if (subCategory == null) {
            throw new RuntimeException("Sub Category not found");
        }

        subCategoryService.deleteSubCategory(subCategory);

        return "Deleted Sub Category : " + subCategory.getSubCategoryName();
    }
}
