package com.theshop.dao;

import com.theshop.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CartDao extends CrudRepository<Cart, Long> {

    @Override
    List<Cart> findAll();
    Cart findCartById(Long id);
}
