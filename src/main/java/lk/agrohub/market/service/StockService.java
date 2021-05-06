package org.fyp.marketplace.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.fyp.marketplace.dtos.ProductDto;
import org.fyp.marketplace.dtos.StockDto;
import org.fyp.marketplace.model.ImageModel;
import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.model.Stock;
import org.fyp.marketplace.repository.ImageRepository;
import org.fyp.marketplace.repository.ProductRepository;
import org.fyp.marketplace.repository.StockRepository;
import org.fyp.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ImageRepository imageRepository;

	public StockDto stocksSearchById(long stockId) {
		Stock s = stockRepository.findById(stockId).get();
		List<ImageModel> images = imageRepository.findByStockId(stockId);
		HashMap<Integer, String> imagePaths = new HashMap<>();
		for (ImageModel i : images) {
			imagePaths.put(i.getOrder(), i.getUrl());
		}
		return new StockDto(s, productRepository.findById(s.getProductId()).get().getProductName(),
				userRepository.findById(s.getProducerId()).get().getUsername(), imagePaths);
	}

	public Stock addStock(Stock stock) {
		stock.setInsertDate(new Date());
		stock.setLastUpdateDate(new Date());
		Stock newStock = this.stockRepository.save(stock);
		return newStock;

	}

	public Stock updateStock(Stock stock) {
		stock.setLastUpdateDate(new Date());
		return this.stockRepository.save(stock);
	}

	public void deleteStock(Stock stock) {
		this.stockRepository.delete(stock);
	}

	public List<StockDto> getAllStocksFiltered(Long productId, Long producerId) {
		List<Stock> stocks = stockRepository.findByMultiple(productId, producerId);
		List<StockDto> stockDtos = new ArrayList<StockDto>();
		for (Stock s : stocks) {
			List<ImageModel> images = imageRepository.findByStockId(s.getId());
			HashMap<Integer, String> imagePaths = new HashMap<>();
			for (ImageModel i : images) {
				imagePaths.put(i.getOrder(), i.getUrl());
			}
			stockDtos.add(new StockDto(s, productRepository.findById(s.getProductId()).get().getProductName(),
					userRepository.findById(s.getProducerId()).get().getUsername(), imagePaths));
		}
		return stockDtos;
	}

}
