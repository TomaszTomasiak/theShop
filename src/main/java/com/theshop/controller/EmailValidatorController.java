package com.theshop.controller;

import com.theshop.client.EmailValidatorApiClient;
import com.theshop.domain.dto.emailValidator.EmailValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/mailValidation")
public class EmailValidatorController {

    @Autowired
    EmailValidatorApiClient client;

    @GetMapping(value = "{mail}")
    public EmailValidatorDto checkMail(@PathVariable("mail") String mail) {
        return client.validateEmail(mail);
    }
}
