package com.theshop.dao;

import com.theshop.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {

    ProductGroup findByName(String name);

    Optional<ProductGroup> findById(Long groupId);

    List<ProductGroup> findAll();

}
