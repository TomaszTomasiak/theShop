package com.theshop.dao;

import com.theshop.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<Order, Long> {

    @Override
    List<Order> findAll();
}
