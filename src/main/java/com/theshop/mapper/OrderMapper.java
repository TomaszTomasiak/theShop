package com.theshop.mapper;

import com.theshop.dao.CartDao;
import com.theshop.dao.OrderDao;
import com.theshop.dao.UserDao;
import com.theshop.domain.Order;
import com.theshop.domain.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    CartDao cartDao;

    @Autowired
    UserDao userDao;

    public Order mapToOrder(final OrderDto orderDto) {
        Order orderBean = new Order();

        orderBean.setId(orderDto.getId());
        orderBean.setOrdered(orderDto.getOrdered());
        orderBean.setComments(orderDto.getComments());
        orderBean.setCart(cartDao.findCartById(orderDto.getCartId()));
        orderBean.setUser(userDao.findById(orderDto.getUserId()).orElse(null));
        orderBean.setTotalValue(orderDto.getTotalValue());
        orderBean.setCompleted(orderDto.isCompleted());
       return orderBean;
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrdered(),
                order.getComments(),
                order.getCart().getId(),
                order.getUser().getId(),
                order.getTotalValue(),
                order.isCompleted()
        );
    }

    public List<Order> mapToOrderList(final List<OrderDto> orderDtoList) {
        return orderDtoList.stream()
                .map(this::mapToOrder)
                .collect(Collectors.toList());
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
