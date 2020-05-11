package com.theshop.resourcedata;

import com.theshop.domain.dto.OrderDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDtoCreator {

    public static long ORDER_ID = 2L;
    public static long UPDATED_ORDER_ID = 4L;
    public static LocalDate ORDERED = LocalDate.now();
    public static LocalDate UPDATED_ORDERED = LocalDate.now().plusDays(2);
    public static String COMMENTS = "comments";
    public static String UPDATED_COMMENTS = "updated comments";
    public static Long CART_ID = 7L;
    public static Long UPDATED_CART_ID = 58L;
    public static Long USER_ID = 6L;
    public static Long UPDATED_USER_ID = 1L;
    public static BigDecimal TOTAL_VALUE = new BigDecimal(2548.85);
    public static BigDecimal UPDATED_TOTAL_VALUE = new BigDecimal(786.02);
    public static boolean IS_COMPLITED = true;
    public static boolean UPDATED_IS_COMPLITED = false;

    public static OrderDto orderDtoCreator() {
        return OrderDto.builder()
                .id(ORDER_ID)
                //.ordered(ORDERED)
                .comments(COMMENTS)
                .cartId(CART_ID)
                .userId(USER_ID)
                .totalValue(TOTAL_VALUE)
                .isCompleted(IS_COMPLITED)
                .build();
    }

    public static OrderDto updatedOrderDtoCreator() {
        return OrderDto.builder()
                .id(ORDER_ID)
                //.ordered(ORDERED)
                .comments(UPDATED_COMMENTS)
                .cartId(CART_ID)
                .userId(USER_ID)
                .totalValue(UPDATED_TOTAL_VALUE)
                .isCompleted(UPDATED_IS_COMPLITED)
                .build();
    }
}
