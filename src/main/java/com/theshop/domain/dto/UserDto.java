package com.theshop.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String mailAdress;
    private String phoneNumber;
    private String password;
    private int status;
    private String userKey;
}
