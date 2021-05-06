package lk.agrohub.market.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.agrohub.market.dtos.ProductDto;
import lk.agrohub.market.dtos.StockDto;
import lk.agrohub.market.model.ImageModel;
import lk.agrohub.market.model.Product;
import lk.agrohub.market.model.Stock;
import lk.agrohub.market.repository.ImageRepository;
import lk.agrohub.market.repository.ProductRepository;
import lk.agrohub.market.repository.StockRepository;
import lk.agrohub.market.repository.UserRepository;

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
