package com.theshop.resourcedata;

import com.theshop.domain.dto.CartDto;

public class CartDtoCreator {

    public static Long CART_ID = 3L;
    public static Long UPDATED_CART_ID = 7L;

    public static CartDto cartDtoCreator() {
        return CartDto.builder()
                .id(CART_ID)
                .build();
    }

    public static CartDto updatedCartDtoCreator() {
        return CartDto.builder()
                .id(UPDATED_CART_ID)
                .build();
    }

}