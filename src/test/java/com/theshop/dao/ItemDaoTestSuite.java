package com.theshop.dao;

import com.theshop.domain.Cart;
import com.theshop.domain.Item;
import com.theshop.domain.Product;
import com.theshop.domain.ProductGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDaoTestSuite {

    @Autowired
    ItemDao itemDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    ProductGroupDao productGroupDao;

    @Test
    public void testItemDaoSave() {

        //Given
        ProductGroup productGroupBuild = ProductGroup.builder()
                .name("food")
                .build();
        productGroupDao.save(productGroupBuild);

        Product productBuild = Product.builder()
                .name("kawa")
                .price(15.6)
                .build();
        productDao.save(productBuild);

        Item itemBuild = Item.builder()
                .product(productBuild)
                .quantity(5)
                .build();

        //When
        itemDao.save(itemBuild);

        //Then
        assertEquals(5, itemBuild.getQuantity());
    }
}
