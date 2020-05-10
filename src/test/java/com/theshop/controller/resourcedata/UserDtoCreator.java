package com.theshop.controller.resourcedata;

import com.theshop.domain.dto.UserDto;

public class UserDtoCreator {

    public static long USER_ID = 5L;
    public static long UPDATED_USER_ID = 87L;


    public static String FIRST_NAME = "John";
    public static String UPDATED_NAME = "Maryla";
    public static String LAST_NAME = "Rambo";
    public static String UPDATED_LAST_NAME = "Rodowicz";
    public static String MAIL = "john.rambo@test.pl";
    public static String UPDATED_MAIL = "maryla.rodowicz@test.pl";
    public static String PHONE = "123456789";
    public static String UPDATED_PHONE = "987654321";
    public static String PASSWORD = "password";
    public static String UPDATED_PASSWORD = "updated_password";

    public static UserDto userDtoCreator() {
        return UserDto.builder()
                .id(USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .mailAdress(MAIL)
                .password(PASSWORD)
                .phoneNumber(PHONE)
                .build();
    }

    public static UserDto updatedUserDtoCreator() {
        return UserDto.builder()
                .id(USER_ID)
                .firstName(UPDATED_NAME)
                .lastName(UPDATED_LAST_NAME)
                .mailAdress(UPDATED_MAIL)
                .password(UPDATED_PASSWORD)
                .phoneNumber(UPDATED_PHONE)
                .build();
    }


}
