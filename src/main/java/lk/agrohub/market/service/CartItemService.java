package lk.agrohub.market.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import lk.agrohub.market.model.CartItem;
import lk.agrohub.market.repository.CartItemRepository;

import java.util.Date;
import java.util.List;

@Service
public class CartItemService {
    final CartItemRepository cartItemRepository;
    final ProductService productService;

    public CartItemService(CartItemRepository cartItemRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem cartItemSearchByCustomerId(long customerId) {
        return cartItemRepository.findByCustomerId(customerId);
    }

    public CartItem addCartItem(CartItem cartItem) {

        CartItem newCartItem = this.cartItemRepository.save(cartItem);
        return newCartItem;
    }

    public CartItem updateCartItem(CartItem cartItem) {

        return this.cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(CartItem cartItem) {
        this.cartItemRepository.delete(cartItem);
    }

}
