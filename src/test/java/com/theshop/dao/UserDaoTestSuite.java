package com.theshop.dao;

import com.theshop.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTestSuite {

    @Autowired
    UserDao userDao;

    @Test
    public void testUserDaoSave() {
        //Given
        User user = new User();
                user.setFirstName("John");
                user.setLastName("Rambo");
                user.setMailAdress("john.rambo@gmail.com");
                user.setPassword("firstblood");
                user.setPhoneNumber("123123123");

        //When
        userDao.save(user);
        //List<User> users = userDao.findAll();
        Long id = user.getId();

        //Then
        assertTrue(userDao.count() > 0);
        assertFalse(userDao.findAll().size() == 0);
        assertEquals("John", user.getFirstName());

        //CleanUp
        userDao.deleteAll();
    }
}
