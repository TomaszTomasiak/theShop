package com.theshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductException extends Exception {

    public static String ERR_PRODUCT_NOT_FOUND= "Product does not exists";

    public ProductException(String message){
        super(message);
    }
}
