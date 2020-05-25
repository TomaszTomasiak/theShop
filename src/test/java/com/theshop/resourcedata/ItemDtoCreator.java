package com.theshop.resourcedata;

import com.theshop.domain.dto.ItemDto;

public class ItemDtoCreator {

    public static long PRODUCT_ID = 17L;
    public static long UPDATED_PRODUCT_ID = 31L;
    public static long ITEM_ID = 5L;
    public static long ORDER_ID = 23L;
    public static long UPDATED_ORDER_ID = 3L;
    public static int ITEM_QTY = 6;
    public static int UPDATED_ITEM_QTY = 3;

    public static ItemDto createItemDto() {
        return ItemDto.builder()
                .id(ITEM_ID)
                .productId(PRODUCT_ID)
                .quantity(ITEM_QTY)

                .build();
    }

    public static ItemDto createUpdatedItemDto() {
        return ItemDto.builder()
                .id(ITEM_ID)
                .productId(UPDATED_PRODUCT_ID)
                .quantity(UPDATED_ITEM_QTY)
                .build();
    }
}
