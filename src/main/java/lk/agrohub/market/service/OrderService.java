package lk.agrohub.market.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import lk.agrohub.market.model.Order;
import lk.agrohub.market.repository.OrderRepository;

@Service
public class OrderService {
	final OrderRepository orderRepository;
	final StockService stockService;
	private String[] paymentType;

	/**
	 * 
	 * @param orderRepository
	 * @param stockService
	 * @param accountService
	 */
	public OrderService(OrderRepository orderRepository, StockService stockService) {
		this.orderRepository = orderRepository;
		this.stockService = stockService;
		this.setPaymentType(new String[] { "cash", "online", "card", "coupon", "other" });
	}

	/**
	 * List all orders
	 * 
	 * @return
	 */
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public List<Order> orderSearchByStockId(long stockId) {
		return orderRepository.findByStockId(stockId);
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

	public String[] getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String[] paymentType) {
		this.paymentType = paymentType;
	}
}
