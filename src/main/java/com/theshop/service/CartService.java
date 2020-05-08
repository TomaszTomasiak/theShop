package com.theshop.service;

import com.theshop.dao.CartDao;
import com.theshop.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    public List<Cart> getCarts() {
        return cartDao.findAll();
    }

    public Cart getCart(final long id) {
        return cartDao.findById(id).orElse(null);
    }

    public Cart save(final Cart cart) {
        return cartDao.save(cart);
    }

    public void delete(final long id) {
        cartDao.deleteById(id);
    }
}
