package com.theshop.dao;

import com.theshop.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {

    @Override
    List<ProductGroup> findAll();


}
