package com.theshop.domain;

import com.theshop.domain.dto.emailValidator.EmailValidatorDto;
import lombok.Getter;

@Getter
public class CheckEmailsValidation {

    private long userId;
    private String mail;
    private boolean valid;

    public CheckEmailsValidation(User user, EmailValidatorDto emailValidatorDto) {
        this.userId = user.getId();
        this.mail = user.getMailAdress();
        this.valid = emailValidatorDto.isValid();
    }
}
