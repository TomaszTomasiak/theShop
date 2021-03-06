package com.theshop.controller;

import com.theshop.domain.dto.OrderDto;
import com.theshop.exception.CartExceptionNotFound;
import com.theshop.exception.NotFoundException;
import com.theshop.exception.NullArgumentException;
import com.theshop.exception.UserException;
import com.theshop.mapper.OrderMapper;
import com.theshop.service.OrderService;
import com.theshop.service.TheShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private TheShopService theShopService;

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @GetMapping
    public List<OrderDto> getAllOrders() {
        log.debug("REST request to get all orders");
        return mapper.mapToOrderDtoList(service.getOrders());
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get order with id: {}", id);
        return mapper.mapToOrderDto(service.getOrder(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createdOrder(@RequestBody OrderDto orderDto) throws UserException, NullArgumentException, CartExceptionNotFound {
        log.debug("REST request to create order: {}", orderDto);
        theShopService.createNewOrder(mapper.mapToOrder(orderDto));
        return orderDto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrderById(@PathVariable("id") long id, @RequestBody OrderDto orderDto) {
        log.debug("REST request to update order with id: {}", id);
        return mapper.mapToOrderDto(service.saveOrder(mapper.mapToOrder(orderDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable("id") long id) {
        log.debug("REST request to delete order with id: {}", id);
        service.deleteOrder(id);
    }
}
