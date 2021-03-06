package com.theshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CartExceptionNotFound extends Exception {

    public static String ERR_PRODUCT_NOT_FOUND= "Cannot add product to cart because it does not exists";
    public static String ERR_ITEM_NOT_FOUND= "Cannot update cart because item does not exists";
    public static String ERR_CART_NOT_FOUND = "Cannot find cart by given Id";

    public CartExceptionNotFound(String message){
        super(message);
    }
}
