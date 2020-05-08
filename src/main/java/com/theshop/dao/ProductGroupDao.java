package com.theshop.dao;

import com.theshop.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {
}
