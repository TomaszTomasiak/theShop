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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartDaoTestSuite {

    @Autowired
    ProductGroupDao productGroupDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    CartDao cartDao;

    @Test
    public void testCartDaoSave() {

        //Given
        ProductGroup productGroupBuild = ProductGroup.builder()
                .name("budowlane")
                .build();
        productGroupDao.save(productGroupBuild);

        Product productBuild = Product.builder()
                .name("kilof")
                .description("description")
                .group(productGroupBuild)
                .price(89.99)
                .build();
        productDao.save(productBuild);

        Item itemBuild = Item.builder()
                .product(productBuild)
                .quantity(5)
                .build();

        List<Item> items = new ArrayList<>();
        items.add(itemBuild);
        itemDao.save(itemBuild);

        Cart cartBuilder = Cart.builder()
                .build();

        //When
        cartDao.save(cartBuilder);
        long id = cartBuilder.getId();

        //Then
        assertNotEquals(0, cartDao.findCartById(id));

    }
}
