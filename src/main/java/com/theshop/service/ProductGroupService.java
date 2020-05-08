package com.theshop.service;

import com.theshop.dao.ProductDao;
import com.theshop.dao.ProductGroupDao;
import com.theshop.domain.Product;
import com.theshop.domain.ProductGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductGroupService {

    @Autowired
    private ProductGroupDao productGroupDao;

    public List<ProductGroup> getGroups() {
        return productGroupDao.findAll();
    }

    public ProductGroup getGroup(final Long id) {
//        if(!productGroupDao.findById(id).isPresent()){
//
//        }
        return productGroupDao.findById(id).orElse(null);
    }

    public ProductGroup save(final ProductGroup productGroup) {
        return productGroupDao.save(productGroup);
    }

    public void deleteGroup(Long id) {
         productGroupDao.deleteById(id);
    }
}
