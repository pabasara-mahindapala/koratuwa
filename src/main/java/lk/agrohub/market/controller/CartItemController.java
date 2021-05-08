package lk.agrohub.market.controller;


import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lk.agrohub.market.model.CartItem;
import lk.agrohub.market.service.CartItemService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/cartItem")
public class CartItemController {
    final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        super();
        this.cartItemService = cartItemService;
    }

    @GetMapping("/all")
    public List<CartItem> listAllCartItem() {
        try {
            return this.cartItemService.getAllCartItems();
        } catch (Exception e) {
            // Log error
            return new ArrayList<CartItem>();
        }
    }

    @GetMapping("/{customerId}")
    public CartItem getCartItemByCustomerId(@PathVariable long customerId) {

        CartItem cartItem = cartItemService.cartItemSearchByCustomerId(customerId);

        // throw exception if null

        if (cartItem == null) {
            throw new RuntimeException("cart is empty");
        }

        return cartItem;
    }



    @PostMapping("/add")
    @PreAuthorize("hasRole('BUYER') or hasRole('ADMIN')")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) throws Exception {
        ResponseEntity<CartItem> result;
        try {
            this.cartItemService.addCartItem(cartItem);
            result = new ResponseEntity<>(cartItem, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(cartItem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('BUYER') or hasRole('ADMIN')")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem) throws Exception {
        ResponseEntity<CartItem> result;
        try {
            this.cartItemService.updateCartItem(cartItem);
            result = new ResponseEntity<>(cartItem, HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(cartItem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/delete/{cartItemId}")
    @PreAuthorize("hasRole('BUYER') or hasRole('ADMIN')")
    public String deleteCartItem(@PathVariable long cartItemId) {

        CartItem cartItem = cartItemService.cartItemSearchByCustomerId(cartItemId);

        // throw exception if null

        if (cartItem == null) {
            throw new RuntimeException("item not found");
        }

        cartItemService.deleteCartItem(cartItem);

        return "Deleted item : " + cartItem.getProductId();
    }

}
