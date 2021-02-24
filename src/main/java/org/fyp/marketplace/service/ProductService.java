package org.fyp.marketplace.service;

import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
	final ProductRepository productRepository;
	final CategoryService categoryService;
	final SubCategoryService subCategoryService;

	public ProductService(ProductRepository productRepository, CategoryService categoryService,
			SubCategoryService subCategoryService) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
		this.subCategoryService = subCategoryService;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public List<Product> getAllProductsFiltered(Long categoryId, Long subCategoryId) {
		return productRepository.findByMultiple(categoryId, subCategoryId);
	}

	public Product productsSearchById(long _id) {
		return productRepository.findById(_id).get();
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
}
