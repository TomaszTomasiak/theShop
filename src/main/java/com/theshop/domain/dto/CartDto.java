package com.theshop.domain.dto;

import com.theshop.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private Long id;
    private Long userId;
    @Builder.Default
    private List<Item> items = new ArrayList<>();
    private Long orderId;


}
