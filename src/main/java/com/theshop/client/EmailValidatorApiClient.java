package com.theshop.client;

import com.theshop.config.EmailValidatorApiConfig;
import com.theshop.domain.dto.emailValidator.EmailValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class EmailValidatorApiClient {

    @Autowired
    private EmailValidatorApiConfig emailValidatorApiConfig;

    @Autowired
    private RestTemplate restTemplate;

    public EmailValidatorDto validateEmail(String email) {
        email = email.toLowerCase();
        URI url = UriComponentsBuilder.fromHttpUrl(emailValidatorApiConfig.getEmailValidatorApiEndpoint() + email)
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", emailValidatorApiConfig.getEmailValidatorApiHost());
        headers.set("x-rapidapi-key", emailValidatorApiConfig.getEmailValidatorApiKey());

        HttpEntity<Object> entity = new HttpEntity(headers);

        ResponseEntity<EmailValidatorDto> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, EmailValidatorDto.class);
        return response.getBody();
    }
}
