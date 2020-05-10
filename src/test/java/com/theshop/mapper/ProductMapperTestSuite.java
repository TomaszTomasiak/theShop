package com.theshop.mapper;


import com.theshop.domain.Product;
import com.theshop.domain.ProductGroup;
import com.theshop.domain.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTestSuite {

    @Autowired
    ProductMapper productMapper;

    private ProductGroup group = ProductGroup.builder()
                .id(1L)
                .name("Test group")
                .build();

    private Product product() {
        return Product.builder()
                .id(1L)
                .name("Shoes")
                .description("nice shoes")
                .price(364.90)
                .group(group)
                .build();
    }

    private ProductDto productDto() {
        return ProductDto.builder()
                .id(52L)
                .name("Pijama")
                .description("comfortable")
                .price(125.50)
                .groupId(40L)
                .build();
    }

    @Test
    public void shouldMapToProduct() {
        //Given
        //When
        Product resultProduct = productMapper.mapToProduct(productDto());
        //Then
        assertEquals(productDto().getId(), resultProduct.getId());
        assertEquals(productDto().getPrice(), resultProduct.getPrice(), 0);
        assertEquals(productDto().getName(), resultProduct.getName());
    }

    @Test
    public void shouldMapToProductDto() {
        //Given
        //When
        ProductDto productDto = productMapper.mapToProductDto(product());
        //Then
        assertEquals(product().getId(), productDto.getId());
        assertEquals(product().getName(), productDto.getName());
    }

    @Test
    public void shouldMapToProductList() {
        //Given
        List<ProductDto> productDtos = new ArrayList<>(Arrays.asList(productDto()));
        //When
        List<Product> productList = productMapper.mapToProductList(productDtos);
        ProductDto productDto = productDtos.get(0);
        Product product = null;
        Iterator<Product> iterator = productList.iterator();
        while(iterator.hasNext()) {
             product = iterator.next();
             if(product.equals(product)) {
                 break;
             }
        }
        //Then
        assertEquals(productDtos.size(), productList.size());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getId(), product.getId());
    }

    @Test
    public void shouldMapToProductDtoList() {
        //Given
        List<Product> products = new ArrayList<>(Arrays.asList(product()));
        //When
        List<ProductDto> productDtos = productMapper.mapToProductDtoList(products);
        ProductDto productDto = null;
        Product product = products.get(0);
        Iterator<ProductDto> iterator = productDtos.iterator();
        while(iterator.hasNext()) {
            productDto = iterator.next();
            if(productDto.equals(productDto())) {
                break;
            }
        }
        //Then
        assertEquals(products.size(),productDtos.size());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getId(), product.getId());
    }
}