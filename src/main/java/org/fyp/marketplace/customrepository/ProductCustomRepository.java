package org.fyp.marketplace.customrepository;

import java.util.List;

import org.fyp.marketplace.model.Product;

public interface ProductCustomRepository {
	public List<Product> findByMultiple(Long producerId, Long categoryId, Long subCategoryId);
}
