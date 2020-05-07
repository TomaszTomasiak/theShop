package com.theshop.dao;

import com.theshop.domain.Product;
import com.theshop.domain.ProductGroup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTestSuite {

    @Autowired
    ProductGroupDao productGroupDao;

    @Autowired
    ProductDao productDao;

    @Test
    public void testProductDaoSave() {

        //Given
        ProductGroup productGroup = new ProductGroup();
        productGroup.setName("AGD");
        productGroupDao.save(productGroup);

        Product product = Product.builder()
                .name("toster")
                .description("description")
                .group(productGroup)
                .price(254.9)
                .build();

        //When
        Long groupId = productGroup.getGroupId();
        productDao.save(product);
        Long productId = product.getProductId();

        //Then
        assertEquals(1, productDao.findByName("toster").size());

        //CleanUp
        productDao.deleteById(productId);
        productGroupDao.deleteById(groupId);

    }
}
