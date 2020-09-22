package com.theshop.controller;

import com.google.gson.Gson;
import com.theshop.dao.UserDao;
import com.theshop.domain.User;
import com.theshop.domain.dto.UserDto;
import com.theshop.resource_data.UserDtoCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserController userController;

    private UserDto userDto;
    private UserDao userDao;

    @Before
    public void initTest() {
        userDto = UserDtoCreator.userDtoCreator();
    }

    @Test
    public void schouldFetchEmptyListOfUsers() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        when(userController.getAllUsers()).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfUsers() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        when(userController.getAllUsers()).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(5)))
                .andExpect(jsonPath("$[0].firstName", is(userDto.getFirstName())))
                .andExpect(jsonPath("$[0].mailAdress", is(userDto.getMailAdress())))
                .andExpect(jsonPath("$[0].password", is(userDto.getPassword())));
    }

    @Test
    public void shouldGetUserWithIndicatedId() throws Exception {
        //Given

        when(userController.getUser(userDto.getId())).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/api/v1/users/"+userDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", is(userDto.getPhoneNumber())))
                .andExpect(jsonPath("$.password", is(userDto.getPassword())));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        when(userController.getAllUsers()).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/users/" + userDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateUser() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        UserDto updatedUserDto = UserDtoCreator.updatedUserDtoCreator();
        when(userController.updateUserById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(UserDto.class)))).thenReturn(updatedUserDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedUserDto);

        //When & Then
        mockMvc.perform(put("/api/v1/users/"+userDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(87)))
                .andExpect(jsonPath("$.firstName", is(updatedUserDto.getFirstName())))
                .andExpect(jsonPath("$.mailAdress", is(updatedUserDto.getMailAdress())));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given

        when(userController.createUser(ArgumentMatchers.any(UserDto.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.phoneNumber", is(userDto.getPhoneNumber())))
                .andExpect(jsonPath("$.lastName", is(userDto.getLastName())));
    }

    @Test
    public void shouldNotCreateUserBecauseUserWithIdAlreadyExists() throws Exception {

        userDto.setId(9L);

        when(userController.createUser(ArgumentMatchers.any(UserDto.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);


        mockMvc.perform(post("/api/v1/ecommercee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isNotFound());



    }
}
