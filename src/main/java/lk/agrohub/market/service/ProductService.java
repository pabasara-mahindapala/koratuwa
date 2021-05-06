package lk.agrohub.market.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.agrohub.market.dtos.ProductDto;
import lk.agrohub.market.model.Product;
import lk.agrohub.market.repository.CategoryRepository;
import lk.agrohub.market.repository.ProductRepository;
import lk.agrohub.market.repository.SubCategoryRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SubCategoryRepository subCategoryRepository;

	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for (Product p : products) {
			productDtos.add(new ProductDto(p, categoryRepository.findById(p.getCategoryId()).get().getCategoryName(),
					subCategoryRepository.findById(p.getSubCategoryId()).get().getSubCategoryName()));
		}
		return productDtos;
	}

	public List<ProductDto> getAllProductsFiltered(Long categoryId, Long subCategoryId) {
		List<Product> products = productRepository.findByMultiple(categoryId, subCategoryId);
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for (Product p : products) {
			productDtos.add(new ProductDto(p, categoryRepository.findById(p.getCategoryId()).get().getCategoryName(),
					subCategoryRepository.findById(p.getSubCategoryId()).get().getSubCategoryName()));
		}
		return productDtos;
	}

	public ProductDto productsSearchById(long _id) {
		Product p = productRepository.findById(_id).get();
		return new ProductDto(p, categoryRepository.findById(p.getCategoryId()).get().getCategoryName(),
				subCategoryRepository.findById(p.getSubCategoryId()).get().getSubCategoryName());
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
