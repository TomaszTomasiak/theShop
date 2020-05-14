package com.theshop.exception;

public class UserException extends Exception {

    public static String ERR_USER_NOT_FOUND= "User does not exists";

    public UserException(String message){
        super(message);
    }
}
