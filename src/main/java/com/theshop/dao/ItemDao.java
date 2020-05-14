package com.theshop.dao;

import com.theshop.domain.Cart;
import com.theshop.domain.Item;
import com.theshop.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

    @Override
    List<Item> findAll();

    void deleteByCartAndProduct(Cart cart, Product product);
}
