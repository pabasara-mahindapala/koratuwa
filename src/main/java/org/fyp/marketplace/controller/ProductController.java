package org.fyp.marketplace.controller;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }


    @GetMapping("/all")
    public List<Product> listAllProduct() {
        try {
            return this.productService.getAllProducts();
        } catch (Exception e) {
            // Log error
            return new ArrayList<Product>();
        }
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable ObjectId productId) {

        Product product = productService.productsSearchById(productId);

        // throw exception if null

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        return product;
    }

    @GetMapping("/{producerId}")
    public Product getProductByProducerId(@PathVariable ObjectId producerId) {

        Product product = productService.productSearchByProducerId(producerId);

        // throw exception if null

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        return product;
    }

    @GetMapping("/{categoryId}")
    public Product getProductByCategoryId(@PathVariable ObjectId categoryId) {

        Product product = productService.productSearchByCategoryId(categoryId);

        // throw exception if null

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        return product;
    }

    @GetMapping("/{subCategoryId}")
    public Product getProductBySubCategoryId(@PathVariable ObjectId subCategoryId) {

        Product product = productService.productSearchBySubCategoryId(subCategoryId);

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
    public String deleteProduct(@PathVariable ObjectId productId) {

        Product product = productService.productsSearchById(productId);

        // throw exception if null

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        productService.deleteProduct(product);

        return "Deleted Product : " + product.getProductName();
    }
}
