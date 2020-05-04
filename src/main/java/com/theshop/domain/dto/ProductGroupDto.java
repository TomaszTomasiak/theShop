package com.theshop.domain.dto;

import com.theshop.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupDto {

    private Long id;
    private String name;
    //private List<Product> products;
}
