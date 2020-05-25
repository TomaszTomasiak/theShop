package com.theshop.service;

import com.theshop.domain.Cart;
import com.theshop.domain.Item;
import com.theshop.domain.Order;
import com.theshop.exception.CartExceptionBadRequest;
import com.theshop.exception.CartExceptionNotFound;
import com.theshop.exception.NullArgumentException;
import com.theshop.exception.UserException;
import com.theshop.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TheShopService {

    private final Logger log = LoggerFactory.getLogger(TheShopService.class);

    @Autowired
    private Validator validator;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    public Cart createNewCart(Cart cart) throws CartExceptionBadRequest {
        if (exists(cart.getId())) {
            throw new CartExceptionBadRequest(CartExceptionBadRequest.ERR_CART_EXISTS);
        }
        cart = new Cart();
        return cartService.saveCart(cart);
    }

    public Cart addItemToCard(long cartId, Item item) throws CartExceptionNotFound {
        Cart cart = cartService.getCart(cartId).orElse(new Cart());
        cart.getItems().add(item);
        validator.validateCartItems(cart.getItems());
        return cartService.saveCart(cart);
    }

    public Cart removeItemFromTheCard(long cartId, long itemId) throws CartExceptionNotFound {
        if(!exists(cartId)) {
            throw new CartExceptionNotFound(CartExceptionNotFound.ERR_CART_NOT_FOUND);
        }

        Optional<Cart> cartOptional = cartService.getCart(cartId);
        Cart cart = cartOptional.get();
        cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .forEach(item -> cart.getItems().remove(item));
        validator.validateCartItems(cart.getItems());
        return cartService.saveCart(cart);
    }

    public Cart updateItemFromTheCart(long cartId, long itemId, Item item) throws CartExceptionNotFound {
        if(!exists(cartId)) {
            throw new CartExceptionNotFound(CartExceptionNotFound.ERR_CART_NOT_FOUND);
        }
        Optional<Cart> cartOptional = cartService.getCart(cartId);
        Cart cart = cartOptional.get();
        cart.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .forEach(i -> itemService.saveItem(item));
        validator.validateCartItems(cart.getItems());
        return cartService.saveCart(cart);
    }

    public Order createNewOrder(Order order) throws CartExceptionNotFound, NullArgumentException, UserException {

        validator.validateCartItems(order.getCart().getItems());
        validator.validateUser(order.getUser().getId());
        return orderService.saveOrder(order);
    }

    private boolean exists(Long id) {
        if (!cartService.getCart(id).isPresent()) {
            return false;
        }
        return true;
    }


    public BigDecimal calculateCost(Order order) {
        if(order.getCart() == null) {
            return BigDecimal.ZERO;
        }
        if(order.getCart().getItems() == null) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(order.getCart().getItems().stream()
                .mapToDouble(i->i.getProduct().getPrice() * i.getQuantity())
                .sum()).setScale(2);
    }
}
