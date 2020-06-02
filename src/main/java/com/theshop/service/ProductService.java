package com.theshop.service;

import com.theshop.dao.ProductDao;
import com.theshop.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;

    public List<Product> getProducts() {
        log.debug("Request to get all products");
        return productDao.findAll();
    }

    public Optional<Product> getProduct(long id) {
        log.debug("Request to get product with id : {}", id);
        return productDao.findById(id);
    }

    public Product saveProduct(Product product) {
        log.debug("Request to add product: {}", product);
        return productDao.save(product);
    }

    public void deleteProduct(Long id) {
        log.debug("Request to delete product with id : {}", id);
        productDao.deleteById(id);
    }
}
