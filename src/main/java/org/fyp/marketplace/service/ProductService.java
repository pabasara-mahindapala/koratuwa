package org.fyp.marketplace.service;

import org.bson.types.ObjectId;
import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final CategoryService categoryService;
    final SubCategoryService subCategoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, SubCategoryService subCategoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product productsSearchById(ObjectId _id){return productRepository.findBy_id(_id);}

    public Product productSearchByProducerId(ObjectId producerId) {
        return productRepository.findByProducerId(producerId);
    }
    public Product productSearchByCategoryId(ObjectId categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    public Product productSearchBySubCategoryId(ObjectId subCategoryId) {
        return productRepository.findBySubCategoryId(subCategoryId);
    }

    public Product addProduct(Product product) {
    	product.setInsertDate(new Date());
    	product.setLastUpdateDate(new Date());
        Product newProduct = this.productRepository.save(product);
        return newProduct;
    }

    public Product updateProduct(Product product) {
        product.setLastUpdateDate(new Date());
        return this.productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    public Boolean validateProduct(Product product) {
        Boolean isValid = true;

        if (isValid && product.getAmount() < 1) {
            isValid = false;
        }

        return isValid;
    }
}
