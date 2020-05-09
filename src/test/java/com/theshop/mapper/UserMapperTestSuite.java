package com.theshop.mapper;


import com.theshop.domain.User;
import com.theshop.domain.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper mapper;

    private UserDto userDto = UserDto.builder()
            .id(1L)
            .firstName("John")
            .lastName("Rambo")
            .mailAdress("john.rambo@mail.com")
            .password("password")
            .phoneNumber("123456789")
            .build();

    private User user = User.builder()
            .id(1L)
            .firstName("John")
            .lastName("Rambo")
            .mailAdress("john.rambo@mail.com")
            .password("password")
            .phoneNumber("123456789")
            .build();

    @Test
    public void mapToUser() {
        //Given

        //When
        User user = mapper.mapToUser(userDto);


        //Then
        assertNotNull(user);
        assertEquals(Long.valueOf(1), user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void mapToUserDto() {
        //Given

        //When
        UserDto userDto = mapper.mapToUserDto(user);

        //Then
        assertNotNull(userDto);
        assertEquals(Long.valueOf(1), userDto.getId());
        assertEquals("Rambo", userDto.getLastName());
        assertEquals("john.rambo@mail.com", userDto.getMailAdress());

    }

    @Test
    public void mapToUserDtoList() {
        //Given

        List<User> users = new ArrayList<>();
        users.add(user);

        //When
        List<UserDto> usersDtoList = mapper.mapToUserDtoList(users);

        //Then
        assertNotNull(usersDtoList);
        assertEquals(1, usersDtoList.size());
        assertEquals("John", usersDtoList.get(0).getFirstName());
    }
}