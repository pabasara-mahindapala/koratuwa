package lk.agrohub.market.customrepository;

import java.util.List;

import lk.agrohub.market.model.Stock;

public interface StockCustomRepository {
	List<Stock> findByMultiple(Long productId, Long producerId);
}
