package com.theshop.controller;

import com.google.gson.Gson;
import com.theshop.resourcedata.ItemDtoCreator;
import com.theshop.domain.dto.ItemDto;
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
@WebMvcTest(ItemController.class)
public class ItemControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemController itemController;

    private ItemDto itemDto;

    @Before
    public void initTest() {
        itemDto = ItemDtoCreator.createItemDto();
    }

    @Test
    public void schouldFetchEmptyListOfItems() throws Exception {
        //Given
        List<ItemDto> itemDtos = new ArrayList<>();
        when(itemController.getAllItems()).thenReturn(itemDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfItems() throws Exception {
        //Given
        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        when(itemController.getAllItems()).thenReturn(itemDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(5)))
                .andExpect(jsonPath("$[0].quantity", is(itemDto.getQuantity())))
                .andExpect(jsonPath("$[0].productId", is(17))
                );
    }

    @Test
    public void shouldGetItemWithIndicatedId() throws Exception {
        //Given

        when(itemController.getItem(itemDto.getId())).thenReturn(itemDto);

        //When & Then
        mockMvc.perform(get("/api/v1/items/"+itemDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(5)))
                .andExpect(jsonPath("quantity", is(itemDto.getQuantity())));
    }

    @Test
    public void shouldDeleteItem() throws Exception {
        //Given
        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        when(itemController.getAllItems()).thenReturn(itemDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/items/" + itemDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateItem() throws Exception {
        //Given
        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        ItemDto updatedItemDto = ItemDtoCreator.createUpdatedItemDto();
        when(itemController.updateItemById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(ItemDto.class)))).thenReturn(updatedItemDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedItemDto);

        //When & Then
        mockMvc.perform(put("/api/v1/items/"+itemDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.productId", is(31)));
    }

    @Test
    public void shouldCreateItem() throws Exception {
        //Given

        when(itemController.createItem(ArgumentMatchers.any(ItemDto.class))).thenReturn(itemDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(itemDto);

        //When & Then
        mockMvc.perform(post("/api/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.productId", is(17)));
    }

}
