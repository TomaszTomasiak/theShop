package com.theshop.service;

import com.theshop.domain.User;
import com.theshop.exception.NotFoundException;
import com.theshop.exception.UserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestSuite {

    @Autowired
    private UserService userService;

    private User user = User.builder()
            .firstName("John")
            .lastName("Rambo")
            .mailAdress("john.rambo@mail.com")
            .password("password")
            .phoneNumber("123456789")
            .build();

    @Test
    public void testSaveAndGetUser() {
        //Given
        long numberOfUsersBeforeAddUser = userService.getUsers().size();
        //When
        userService.saveUser(user);
        User testUser = userService.getUser(user.getId()).get();
        long numberOfUsersAfterAddUser = userService.getUsers().size();


        //Then
        assertNotNull(testUser);
        assertEquals("John", testUser.getFirstName());
        assertEquals(1, numberOfUsersAfterAddUser - numberOfUsersBeforeAddUser);
    }

    @Test
    public void testDeleteUser() {
        //Given
        //When
        userService.saveUser(user);
        userService.deleteUser(user.getId());

        //Then
        assertFalse(userService.getUser(user.getId()).isPresent());
    }

    @Test
    public void testReturnUserById() throws NotFoundException {
        //Given
        User userNew = User.builder()
                .firstName("Maryla")
                .lastName("Rodowicz")
                .mailAdress("test@mail.com")
                .password("password2")
                .phoneNumber("222222222")
                .build();

        userService.saveUser(userNew);

        //When
        User resultUser = userService.getUser(userNew.getId()).orElseThrow(NotFoundException::new);
        //Then
        assertEquals(userNew.getId(), resultUser.getId());
        assertEquals(userNew.getLastName(), resultUser.getLastName());
        assertEquals(userNew.getPhoneNumber(), resultUser.getPhoneNumber());
    }
}