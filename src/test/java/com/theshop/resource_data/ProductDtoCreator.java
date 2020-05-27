package com.theshop.resource_data;

import com.theshop.domain.dto.ProductDto;

public class ProductDtoCreator {

    public static long PRODUCT_ID = 17L;
    public static long UPDATED_PRODUCT_ID = 31L;
    public static long GROUP_ID = 83L;
    public static long UPDATED_GROUP_ID = 11L;
    public static String PRODUCT_NAME = "kilof";
    public static String PRODUCT_DESC = "super wytrzymały";
    public static String UPDATED_PRODUCT_DESC = "energooszczędny";
    public static String UPDATED_PRODUCT_NAME = "toster";
    public static double PRODUCT_PRICE = 155.50;
    public static double UPDATED_PRODUCT_PRICE = 292.90;
    public static boolean PRODUCT_AVAILABLE = true;
    public static boolean UPDATE_PRODUCT_AVAILABLE = false;

    public static ProductDto createProductDto () {

        return ProductDto.builder()
                .id(17L)
                .name(PRODUCT_NAME)
                .description(PRODUCT_DESC)
                .groupId(GROUP_ID)
                .price(PRODUCT_PRICE)
                .available(PRODUCT_AVAILABLE)
                .build();
    }

    public static ProductDto createUpdatedProductDto () {

        return ProductDto.builder()
                .id(17L)
                .name(UPDATED_PRODUCT_NAME)
                .description(UPDATED_PRODUCT_DESC)
                .groupId(UPDATED_GROUP_ID)
                .price(UPDATED_PRODUCT_PRICE)
                .available(UPDATE_PRODUCT_AVAILABLE)
                .build();
    }
}
