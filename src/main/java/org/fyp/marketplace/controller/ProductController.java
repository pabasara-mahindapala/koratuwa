package org.fyp.marketplace.controller;

import java.util.ArrayList;
import java.util.List;

import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.service.ProductService;
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
@RequestMapping("/rest/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }


    @GetMapping("/all")
    public List<Product> listAllProduct(@RequestParam(required = false) Long producerId, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long subCategoryId) {
        try {
        	return this.productService.getAllProductsFiltered(producerId, categoryId, subCategoryId);
        } catch (Exception e) {
            // Log error
            return new ArrayList<Product>();
        }
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable long productId) {

        Product product = productService.productsSearchById(productId);

        // throw exception if null

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        return product;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws Exception {
        ResponseEntity<Product> result;
        try {
            this.productService.addProduct(product);
            result = new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(product, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
        ResponseEntity<Product> result;
        try {
            this.productService.updateProduct(product);
            result = new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(product, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public String deleteProduct(@PathVariable long productId) {

        Product product = productService.productsSearchById(productId);

        // throw exception if null

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        productService.deleteProduct(product);

        return "Deleted Product : " + product.getProductName();
    }
}
