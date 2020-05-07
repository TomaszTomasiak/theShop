package com.theshop.dao;

import com.theshop.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);
}
