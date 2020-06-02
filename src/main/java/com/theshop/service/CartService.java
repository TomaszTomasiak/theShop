package com.theshop.service;

import com.theshop.dao.CartDao;
import com.theshop.domain.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CartService {

    private final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartDao cartDao;

    public List<Cart> getCarts() {
        log.debug("Request to get all carts");
        return cartDao.findAll();
    }

    public Optional<Cart> getCart(long id) {
        log.debug("Request to get cart with id {} = ", id);
        return cartDao.findById(id);
    }

    public Cart saveCart(Cart cart) {
        log.debug("Request to create new cart: {}", cart);
        return cartDao.save(cart);
    }

    public void deleteCart(long id) {
        log.debug("Request to delete cart with id :{}", id);
        cartDao.deleteById(id);
    }

    public boolean exists(Long id) {
        if(!cartDao.findById(id).isPresent()) {
            return false;
        }
        return true;
    }
}
