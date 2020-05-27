package com.theshop.controller;

import com.google.gson.Gson;
import com.theshop.domain.dto.CartDto;
import com.theshop.resource_data.CartDtoCreator;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartController cartController;

    private CartDto cartDto;

    @Before
    public void initTests() {
        cartDto = CartDtoCreator.cartDtoCreator();
    }

    @Test
    public void schouldFetchEmptyListOfCarts() throws Exception {
        //Given
        List<CartDto> cartDtos = new ArrayList<>();
        when(cartController.getAllCarts()).thenReturn(cartDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/carts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfCarts() throws Exception {
        //Given
        List<CartDto> cartDtos = new ArrayList<>();
        cartDtos.add(cartDto);
        when(cartController.getAllCarts()).thenReturn(cartDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/carts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(3)));
    }

    @Test
    public void shouldGetCartWithIndicatedId() throws Exception {
        //Given

        when(cartController.getCart(cartDto.getId())).thenReturn(cartDto);

        //When & Then
        mockMvc.perform(get("/api/v1/carts/"+cartDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)));
    }

    @Test
    public void shouldDeleteCart() throws Exception {
        //Given
        List<CartDto> cartDtos = new ArrayList<>();
        cartDtos.add(cartDto);
        when(cartController.getAllCarts()).thenReturn(cartDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/carts/" + cartDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateCart() throws Exception {
        //Given
        List<CartDto> cartDtos = new ArrayList<>();
        cartDtos.add(cartDto);
        CartDto updatedCartDto= CartDtoCreator.updatedCartDtoCreator();
        when(cartController.updateCartById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(CartDto.class)))).thenReturn(updatedCartDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedCartDto);

        //When & Then
        mockMvc.perform(put("/api/v1/carts/"+cartDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(7)));
    }

    @Test
    public void shouldCreateCart() throws Exception {
        //Given

        when(cartController.createNewCart(ArgumentMatchers.any(CartDto.class))).thenReturn(cartDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartDto);

        //When & Then
        mockMvc.perform(post("/api/v1/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(3)));
    }
}
