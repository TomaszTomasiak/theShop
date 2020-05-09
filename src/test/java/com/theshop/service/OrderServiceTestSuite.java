package com.theshop.service;

import com.theshop.domain.Cart;
import com.theshop.domain.Order;
import com.theshop.domain.User;
import com.theshop.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTestSuite {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Test
    public void testSaveAndGetOrder() throws NotFoundException {
        //Given
        User user = User.builder()
                .firstName("John")
                .lastName("Rambo")
                .mailAdress("john.rambo@mail.com")
                .password("password")
                .phoneNumber("123456789")
                .build();

        userService.saveUser(user);

        Cart cart = Cart.builder()
                .build();

        cartService.saveCart(cart);

        Order order = Order.builder()
                .ordered(LocalDate.now())
                .user(user)
                .cart(cart)
                .build();

        //When
        orderService.saveOrder(order);
        Order tempOrder = orderService.getOrder(order.getId()).orElseThrow(NotFoundException::new);
        //Then
        assertEquals(order.getId(),tempOrder.getId());
    }

    @Test
    public void testDeleteOrder()  {
        //Given
        User user = User.builder()
                .firstName("John")
                .lastName("Rambo")
                .mailAdress("john.rambo@mail.com")
                .password("password")
                .phoneNumber("123456789")
                .build();

        userService.saveUser(user);

        Cart cart = Cart.builder()
                .build();

        cartService.saveCart(cart);

        Order order = Order.builder()
                .ordered(LocalDate.now())
                .user(user)
                .cart(cart)
                .build();
        //When
        orderService.saveOrder(order);
        List<Order> ordersAfterAdd = orderService.getOrders();
        orderService.deleteOrder(order.getId());
        List<Order> ordersAfterRemove = orderService.getOrders();
        //Then
        assertTrue(ordersAfterAdd.size() - ordersAfterRemove.size() == 1);
    }
}