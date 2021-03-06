package com.theshop.mapper;

import com.theshop.dao.ProductGroupDao;
import com.theshop.domain.Product;
import com.theshop.domain.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    ProductGroupDao productGroupDao;

    public Product mapToProduct(final ProductDto productDto) {
        Product productBean = new Product();
        productBean.setId(productDto.getId());
        productBean.setName(productDto.getName());
        productBean.setPrice(productDto.getPrice());
        productBean.setDescription(productDto.getDescription());
        productBean.setGroup(productGroupDao.findById(productDto.getGroupId()).orElse(null));
        productBean.setAvailable(productDto.isAvailable());
        return productBean;
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getGroup().getId(),
                product.isAvailable()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }
}
