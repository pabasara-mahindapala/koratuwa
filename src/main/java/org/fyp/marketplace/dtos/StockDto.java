package org.fyp.marketplace.dtos;

import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.model.Stock;

public class StockDto {
	private Stock stock;
	
	private String productName;
	
	private String producerName;

	public StockDto(Stock stock, String productName, String producerName) {
		super();
		this.stock = stock;
		this.productName = productName;
		this.producerName = producerName;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	
	
	
	
}
