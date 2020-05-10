package com.theshop.controller.resourcedata;

import com.theshop.domain.dto.ProductGroupDto;

public class ProductGroupDtoCreator {
    private static long GROUP_ID = 83L;
    private static long UPDATED_GROUP_ID = 11L;


    public static String GROUP_NAME = "budowlane";
    public static String UPDATED_GROUP_NAME = "agd";

    public static ProductGroupDto createProductGroupDto() {
        return ProductGroupDto.builder()
                .id(GROUP_ID)
                .name(GROUP_NAME)
                .build();
    }

    public static ProductGroupDto createpdatedProductGroupDto() {
        return ProductGroupDto.builder()
                .id(GROUP_ID)
                .name(UPDATED_GROUP_NAME)
                .build();
    }
}
