package com.theshop.controller;

import com.google.gson.Gson;
import com.theshop.controller.resourcedata.ProductDtoCreator;
import com.theshop.domain.dto.ProductDto;
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
@WebMvcTest(ProductController.class)
public class ProductControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductController productController;

    private ProductDto productDto;
    private ProductDto updatedProductDto;

    @Before
    public void initTest() {
        productDto = ProductDtoCreator.createProductDto();
        updatedProductDto = ProductDtoCreator.createUpdatedProductDto();
    }

    @Test
    public void schouldFetchEmptyListOfProductss() throws Exception {
        //Given
        List<ProductDto> productDtos = new ArrayList<>();
        when(productController.getAllProducts()).thenReturn(productDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/theshop/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfProducts() throws Exception {
        //Given
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        when(productController.getAllProducts()).thenReturn(productDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/theshop/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(17)))
                .andExpect(jsonPath("$[0].name", is(productDto.getName())))
                .andExpect(jsonPath("$[0].price", is(productDto.getPrice()))
                );
    }

    @Test
    public void shouldGetProductWithIndicatedId() throws Exception {
        //Given

        when(productController.getProduct(ProductDtoCreator.PRODUCT_ID)).thenReturn(productDto);

        //When & Then
        mockMvc.perform(get("/api/v1/theshop/products/"+productDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(productDto.getName())))
                .andExpect(jsonPath("description", is(productDto.getDescription())));
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        //Given
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        when(productController.getAllProducts()).thenReturn(productDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/theshop/products/" + productDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateProduct() throws Exception {
        //Given
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        when(productController.updateProductById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(ProductDto.class)))).thenReturn(updatedProductDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedProductDto);

        //When & Then
        mockMvc.perform(put("/api/v1/theshop/products/"+productDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(17)))
                .andExpect(jsonPath("$.name", is(ProductDtoCreator.UPDATED_PRODUCT_NAME)))
                .andExpect(jsonPath("$.groupId", is(11)));
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        //Given

        when(productController.createProduct(ArgumentMatchers.any(ProductDto.class))).thenReturn(productDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(productDto);

        //When & Then
        mockMvc.perform(post("/api/v1/theshop/products")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(17)))
                .andExpect(jsonPath("$.name", is(productDto.getName())))
                .andExpect(jsonPath("$.price", is(productDto.getPrice())));
    }
}
