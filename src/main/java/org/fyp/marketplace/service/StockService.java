package org.fyp.marketplace.service;

import java.util.Date;
import java.util.List;

import org.fyp.marketplace.model.Product;
import org.fyp.marketplace.model.Stock;
import org.fyp.marketplace.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	final StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	public Stock stocksSearchById(long stockId) {
		return stockRepository.findById(stockId).get();
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

	public List<Stock> getAllStocksFiltered(Long productId, Long producerId) {
		return this.stockRepository.findByMultiple(productId, producerId);
	}

}
