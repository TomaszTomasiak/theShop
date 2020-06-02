package com.theshop.controller;

import com.google.gson.Gson;
import com.theshop.domain.dto.ProductGroupDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductGroupController.class)
public class ProductGroupControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductGroupController productGroupController;

    @Test
    public void schouldFetchEmptyListOfGroups() throws Exception {
        //Given
        List<ProductGroupDto> productGroupDtos = new ArrayList<>();
        when(productGroupController.getAllGroups()).thenReturn(productGroupDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/groups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfGroups() throws Exception {
        //Given
        List<ProductGroupDto> productGroupDtos = new ArrayList<>();
        productGroupDtos.add(new ProductGroupDto(15L, "group name"));
        when(productGroupController.getAllGroups()).thenReturn(productGroupDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/groups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(15)))
                .andExpect(jsonPath("$[0].name", is("group name")));
    }

    @Test
    public void shouldGetGroupWithIndicatedId() throws Exception {
        //Given
        ProductGroupDto productGroupDto = new ProductGroupDto(15L, "group name");
        when(productGroupController.getGroup(15L)).thenReturn(productGroupDto);

        //When & Then
        mockMvc.perform(get("/api/v1/groups/15").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(15)))
                .andExpect(jsonPath("name", is("group name")));
    }

    @Test
    public void shouldDeleteGroup() throws Exception {
        //Given
        List<ProductGroupDto> productGroupDtos = new ArrayList<>();
        productGroupDtos.add(new ProductGroupDto(15L, "group name"));
        when(productGroupController.getAllGroups()).thenReturn(productGroupDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/groups/15").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateGroup() throws Exception {
        //Given
        List<ProductGroupDto> productGroupDtos = new ArrayList<>();
        productGroupDtos.add(new ProductGroupDto(15L, "group name"));
        ProductGroupDto productGroupDtoUpdated = new ProductGroupDto(15L, "updated group name");
        when(productGroupController.updateGroupById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(ProductGroupDto.class)))).thenReturn(productGroupDtoUpdated);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(productGroupDtoUpdated);

        //When & Then
        mockMvc.perform(put("/api/v1/groups/15")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(15)))
                .andExpect(jsonPath("$.name", is("updated group name")));
    }

    @Test
    public void shouldCreateGroup() throws Exception {
        //Given
        ProductGroupDto productGroupDto = new ProductGroupDto(15L, "group name");
        when(productGroupController.createGroup(ArgumentMatchers.any(ProductGroupDto.class))).thenReturn(productGroupDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(productGroupDto);

        //When & Then
        mockMvc.perform(post("/api/v1/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(15)))
                .andExpect(jsonPath("$.name", is("group name")));
    }
}
