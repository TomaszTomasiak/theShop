package com.theshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductGroupException extends Exception {

    public static String ERR_PRODUCTS_SIGNED_TO_GROUP = "Cannot delate group because there are products signed to the group";


    public ProductGroupException(String message){
        super(message);
    }
}
