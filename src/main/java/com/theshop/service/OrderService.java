package com.theshop.service;

import com.theshop.dao.OrderDao;
import com.theshop.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<Order> getOrders() {
        return orderDao.findAll();
    }

    public Order getOrder(final long id) {
        return orderDao.findById(id).orElse(null);
    }

    public Order save(final Order order) {
        return orderDao.save(order);
    }

    public void delete(final long id) {
        orderDao.deleteById(id);
    }
}
