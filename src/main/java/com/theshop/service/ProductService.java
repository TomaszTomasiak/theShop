package com.theshop.service;

import com.theshop.dao.ProductDao;
import com.theshop.domain.Product;
import com.theshop.domain.ProductGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getProducts() {
        return productDao.findAll();
    }

    public Product getProduct(long id) {
        return productDao.findById(id).orElse(null);
    }

    public Product save(final Product product) {
        return productDao.save(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }



//    public void groupChangeIfGroupDeleted() {
//        ProductGroup group = productGroupDao.findById(id).orElse(null);
//
//        List<Product> productList = productDao.findAll();
//        productList.stream()
//                .filter(product -> product.getGroup().equals(group))
//                .forEach(product -> product.setGroup(null));
//    }
}
