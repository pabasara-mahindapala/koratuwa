package lk.agrohub.market.customrepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lk.agrohub.market.model.Product;
import lk.agrohub.market.model.Stock;

@Repository
public class StockCustomRepositoryImpl implements StockCustomRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	public List<Stock> findByMultiple(Long productId, Long producerId) {
		final Query query = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		if (productId != null)
			criteria.add(Criteria.where("productId").is(productId));
		if (producerId != null)
			criteria.add(Criteria.where("producerId").is(producerId));

		if (!criteria.isEmpty())
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		return mongoTemplate.find(query, Stock.class);
	}
}