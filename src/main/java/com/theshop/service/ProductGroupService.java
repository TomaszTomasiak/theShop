package com.theshop.service;

import com.theshop.dao.ProductDao;
import com.theshop.dao.ProductGroupDao;
import com.theshop.domain.Product;
import com.theshop.domain.ProductGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductGroupService {


    private final Logger log = LoggerFactory.getLogger(ProductGroupService.class);

    @Autowired
    private ProductGroupDao productGroupDao;

    public List<ProductGroup> getGroups() {
        log.debug("Request to get all groups ");
        return productGroupDao.findAll();
    }

    public Optional<ProductGroup> getGroup(Long id) {
        log.debug("Request to get group with id: ", id);
        return productGroupDao.findById(id);
    }

    public ProductGroup saveGroup(ProductGroup productGroup) {
        log.debug("Request to create group: ", productGroup);
        return productGroupDao.save(productGroup);
    }

    public void deleteGroup(Long id) {
        log.debug("Request to delete group with id: ", id);
        productGroupDao.deleteById(id);
    }

    public void deleteGroupAndImputeDefoultGroup() {

    }
}
