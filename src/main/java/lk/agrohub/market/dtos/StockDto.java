package lk.agrohub.market.dtos;

import java.util.HashMap;
import java.util.List;

import lk.agrohub.market.model.Product;
import lk.agrohub.market.model.Stock;

public class StockDto {
	private Stock stock;

	private String productName;

	private String producerName;

	private HashMap<Integer, String> imagePaths;

	public StockDto(Stock stock, String productName, String producerName, HashMap<Integer, String> imagePaths) {
		super();
		this.stock = stock;
		this.productName = productName;
		this.producerName = producerName;
		this.imagePaths = imagePaths;
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

	public HashMap<Integer, String> getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(HashMap<Integer, String> imagePaths) {
		this.imagePaths = imagePaths;
	}

}
