package com.theshop.dao;

import com.theshop.domain.*;
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
public class OrderDaoTestSuite {

    @Autowired
    CartDao cartDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    ProductGroupDao productGroupDao;

    @Test
    public void testOrderDaoSave() {
        //Given
        ProductGroup productGroupTest = ProductGroup.builder()
                .name("AGD")
                .build();
        productGroupDao.save(productGroupTest);
        long groupId = productGroupTest.getId();

        Product productTest = Product.builder()
                .name("toster")
                .description("description")
                .group(productGroupTest)
                .price(254.9)
                .build();
        productDao.save(productTest);
        long productId = productTest.getId();

        User userTest = User.builder()
                .firstName("John")
                .lastName("Rambo")
                .mailAdress("john.rambo@gmail.com")
                .password("firstblood")
                .phoneNumber("123123123")
                .build();
        userDao.save(userTest);
        long userId = userTest.getId();

        Item itemTest = Item.builder()
                .product(productTest)
                .quantity(7)
                .build();

        itemDao.save(itemTest);
        long itemId = itemTest.getId();

        List<Item> itemList = new ArrayList<>();
        itemList.add(itemTest);

        Cart cartTest = Cart.builder()
                .build();
        cartDao.save(cartTest);
        long cartId = cartTest.getId();

        Order orderTest = Order.builder()
                .user(userTest)
                .build();

        //When
        orderDao.save(orderTest);
        long id = orderTest.getId();

        //Then
        assertEquals(1, itemList.size());
        assertNotEquals(0, orderDao.findAll().size());


        //CleanUp
//        productGroupDao.deleteById(groupId);
//        productDao.deleteById(productId);
//        userDao.deleteById(userId);
//        itemDao.deleteById(itemId);
//        cartDao.deleteById(cartId);
//        orderDao.deleteById(id);

    }
}
