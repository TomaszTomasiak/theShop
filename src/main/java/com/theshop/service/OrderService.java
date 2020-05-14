package com.theshop.service;

import com.theshop.dao.CartDao;
import com.theshop.dao.OrderDao;
import com.theshop.dao.UserDao;
import com.theshop.domain.Cart;
import com.theshop.domain.Order;
import com.theshop.domain.User;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public List<Order> getOrders() {
        log.debug("Request to get all orders");
        return orderDao.findAll();
    }

    public Optional<Order> getOrder(long id) {
        log.debug("Request to get order with id: {}", id);
        return orderDao.findById(id);
    }

    public Order saveOrder(Order order) {
        log.debug("Request to create order: {}", order);
        return orderDao.save(order);
    }

    public void deleteOrder(long id) {
        log.debug("Request to delete order with id: {}", id);
        orderDao.deleteById(id);
    }
}
