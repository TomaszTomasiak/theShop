package com.theshop.mapper;


import com.theshop.domain.*;
import com.theshop.domain.dto.OrderDto;
import com.theshop.exception.UserException;
import com.theshop.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTestSuite {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CartService cartService;

    private User user = User.builder()
            .id(1L)
            .firstName("John")
            .lastName("Rambo")
            .mailAdress("john.rambo@mail.com")
            .password("password")
            .phoneNumber("123456789")
            .build();

    private ProductGroup group = ProductGroup.builder()
            .id(1L)
            .name("Test group")
            .build();

    private Product product = Product.builder()
            .id(1L)
            .name("Shoes")
            .description("nice shoes")
            .price(364.90)
            .group(group)
            .build();

    private Item item = Item.builder()
            .id(15L)
            .product(product)
            .quantity(5)
            .build();

    private Cart cart = Cart.builder()
            .id(2L)
            .build();


    private Order order = Order.builder()
            .id(69L)
            .ordered(LocalDate.now())
            .comments("fast delivery")
            .user(user)
            .cart(cart)
            .totalValue(new BigDecimal(258))
            .isCompleted(false)
            .build();

    private OrderDto orderDto = OrderDto.builder()
            .id(22L)
            .ordered(LocalDate.now())
            .comments("havy")
            .userId(33L)
            .cartId(15L)
            .totalValue(new BigDecimal(999))
            .isCompleted(true)
            .build();

    @Test
    public void shouldMapToOrder() {
        //Given
        //When
            Order order = orderMapper.mapToOrder(orderDto);
            //Then
            assertEquals(orderDto.getOrdered(), order.getOrdered());
            assertEquals(orderDto.getId(), order.getId());
    }

    @Test
    public void shouldMapToOrderDto() {
        //Given
        //When
        OrderDto orderDto = orderMapper.mapToOrderDto(order);
        //Then
        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getOrdered(), orderDto.getOrdered());
    }

    @Test
    public void shouldMapToOrderDtoList() {
        //Given
        List<Order> orders = new ArrayList<>(Arrays.asList(order));
        List<OrderDto> orderDtos = orderMapper.mapToOrderDtoList(orders);
        //When
        Order order = orders.get(0);
        OrderDto orderDto = orderDtos.get(0);
        //Then
        assertEquals(order.getId(), orderDto.getId());
        assertEquals(orders.size(), orderDtos.size());
    }

}