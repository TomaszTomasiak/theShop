package com.theshop.controller;


import com.theshop.domain.dto.ProductDto;
import com.theshop.exception.NotFoundException;
import com.theshop.mapper.ProductMapper;
import com.theshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper mapper;

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public List<ProductDto> getAllProducts() {
        log.debug("REST request to get all products");
        return mapper.mapToProductDtoList(service.getProducts());
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get product with id: {}", id);
        return mapper.mapToProductDto(service.getProduct(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        log.debug("REST request to add new group: {}", productDto);
        service.saveProduct(mapper.mapToProduct(productDto));
        return productDto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProductById(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        log.debug("REST request to update product with id: {}", id);
        return mapper.mapToProductDto(service.saveProduct(mapper.mapToProduct(productDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") long id) {
        log.debug("REST request to delete product with id: {}", id);
        service.deleteProduct(id);
    }
}
