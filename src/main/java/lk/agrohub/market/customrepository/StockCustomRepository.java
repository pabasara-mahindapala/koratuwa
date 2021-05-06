package org.fyp.marketplace.customrepository;

import java.util.List;

import org.fyp.marketplace.model.Stock;

public interface StockCustomRepository {
	List<Stock> findByMultiple(Long productId, Long producerId);
}
