package lk.agrohub.market.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.agrohub.market.model.Order;
import lk.agrohub.market.repository.OrderRepository;
import lk.agrohub.market.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	/**
	 * List all orders
	 * 
	 * @return
	 */
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public List<Order> orderSearchByProductId(long productId) {
		return orderRepository.findByProductId(productId);
	}

	public Order createOrder(Order order) throws Exception {
		order.setIsActive(true);
		order.setInsertDate(new Date());
		Boolean validation = this.validateOrder(order);
		if (validation) {
			Order newOrder = this.orderRepository.save(order);
			return newOrder;
		} else {
			throw new Exception("Unable to place order, validation fail");
		}
	}

	public Boolean validateOrder(Order order) {
		Boolean isValid = true;

		if (isValid && order.getQuanity() < 1) {
			isValid = false;
		}

		return isValid;
	}
}
