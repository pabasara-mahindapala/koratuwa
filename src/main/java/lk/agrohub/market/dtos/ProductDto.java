package lk.agrohub.market.dtos;

import lk.agrohub.market.model.Product;

public class ProductDto {
	private Product product;
	
	private String categoryName;
	
	private String subCategoryName;
	
	

	public ProductDto(Product product, String categoryName, String subCategoryName) {
		super();
		this.product = product;
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
	
	
}
