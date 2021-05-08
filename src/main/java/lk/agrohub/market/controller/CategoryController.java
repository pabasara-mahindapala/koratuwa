package lk.agrohub.market.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lk.agrohub.market.model.Category;
import lk.agrohub.market.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/category")
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> listAllCategory() {
        try {
            return this.categoryService.getAllCategory();
        } catch (Exception e) {
            // Log error
            return new ArrayList<Category>();
        }
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable long categoryId) {

        Category category = categoryService.getCategoryById(categoryId);

        // throw exception if null

        if (category == null) {
            throw new RuntimeException("Account not found");
        }

        return category;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws Exception {
        ResponseEntity<Category> result;
        try {
            this.categoryService.addCategory(category);
            result = new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(category, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws Exception {
        ResponseEntity<Category> result;
        try {
            this.categoryService.updateCategory(category);
            result = new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(category, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/delete/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCategory(@PathVariable long categoryId) {

        Category category = categoryService.getCategoryById(categoryId);

        // throw exception if null

        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        categoryService.deleteCategory(category);

        return "Deleted Category : " + category.getCategoryName();
    }
}
