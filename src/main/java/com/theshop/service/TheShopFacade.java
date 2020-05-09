package com.theshop.service;

import com.theshop.domain.*;
import com.theshop.exception.CartExceptionBadRequest;
import com.theshop.exception.CartExceptionNotFound;
import com.theshop.exception.NullArgumentException;
import com.theshop.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheShopFacade {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private void validateCartItems(List<Item> itemList) throws CartExceptionNotFound {
        if (itemList == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        for (int i = 0; i < itemList.size(); i++) {
            Optional<Product> product = productService.getProduct(itemList.get(i).getProduct().getProductId());
            if (!product.isPresent()) {
                throw new CartExceptionNotFound(CartExceptionNotFound.ERR_PRODUCT_NOT_FOUND);
            }
        }
    }

    private void validateUser(Long userId) throws UserException, NullArgumentException {
        if (userId == null) {
            throw new NullArgumentException(NullArgumentException.ERR_ARGUMENTS_NULL);
        }
        if (!userService.getUser(userId).isPresent()) {
            throw new UserException(UserException.ERR_USER_NOT_FOUND);
        }
    }

    public Cart createNewCart(Cart cart) throws UserException, NullArgumentException, CartExceptionBadRequest {
        if (cart.getId() != null) {
            if (cartService.exists(cart.getId())) {
                throw new CartExceptionBadRequest(CartExceptionBadRequest.ERR_CART_EXISTS);
            }
        }
        //validateUser(cart.getUserId());
        Cart saved = cartService.saveCart(cart);
        return saved;
    }

//    public Order createOrder(long cartId, Order order) throws UserException, NullArgumentException {
//
//        validateUser(order.getUser().getId());
//        Cart cart = cartService.getCart(cartId);
//        return orderService.save(order);
//    }

/*
public Cart createCart(@RequestParam long userId) {
        Optional<User> user = userRepository.findById(userId);
        Cart cart = new Cart(new ArrayList<>(), user.get());
        Cart savedCart = cartRepository.save(cart);
        return savedCart;
    }
 */
}

