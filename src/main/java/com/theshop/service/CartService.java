package com.theshop.service;

import com.theshop.dao.CartDao;
import com.theshop.dao.ItemDao;
import com.theshop.domain.Cart;
import com.theshop.domain.Item;
import com.theshop.exception.CartExceptionNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CartService {

    private final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ItemDao itemDao;

    public List<Cart> getCarts() {
        log.debug("Request to get all carts");
        return cartDao.findAll();
    }

    public Optional<Cart> getCart(long id) {
        log.debug("Request to get cart with id = ", id);
        return cartDao.findById(id);
    }

    public Cart saveCart(Cart cart) {
        cart = new Cart();
        log.debug("Request to create new cart: ", cart);
        return cartDao.save(cart);
    }

    public void deleteCart(long id) {
        log.debug("Request to delete cart with id = ", id);
        cartDao.deleteById(id);
    }

    public Cart addItemToCard(long cartId, long itemId) {
        log.debug("Request to add item with id = " + itemId + " to cart with id = " + cartId);
        Cart cart = cartDao.findById(cartId).orElse(null);
        cart.getItems().add(itemDao.findById(itemId).orElse(null));
        cartDao.save(cart);
        return cart;
    }

    public Cart removeItemFromCard( long cartId, long itemId) {
        log.debug("Request to delete item with id = " + itemId + " from cart with id = " + cartId);
        Cart cart = cartDao.findById(cartId).orElse(null);

        Optional<Item> itemOptional = itemDao.findById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            cart.getItems().remove(item);
        }

        cartDao.save(cart);
        return cart;
    }

    public boolean exists(Long id) {
        if(!cartDao.findById(id).isPresent()) {
            return false;
        }
        return true;
    }


}
