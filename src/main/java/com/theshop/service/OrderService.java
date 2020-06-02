package com.theshop.service;

import com.theshop.config.AdminConfig;
import com.theshop.dao.OrderDao;
import com.theshop.domain.Mail;
import com.theshop.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

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
        Order createdOrder = orderDao.save(order);
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                "New order",
                "Added new order with total value: " + createdOrder.getTotalValue() + " PLN"
        ));
        return createdOrder;
    }

    public void deleteOrder(long id) {
        log.debug("Request to delete order with id: {}", id);
        orderDao.deleteById(id);
    }
}
