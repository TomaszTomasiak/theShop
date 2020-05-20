package com.theshop.controller;


import com.theshop.client.NbpApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/currency")
public class CurrencyController {

    @Autowired
    private NbpApiClient nbpApiClient;

    @GetMapping(value = "/{code}")
    public double getFactor(@PathVariable("code") String code) {
        return nbpApiClient.getCurrencyFactor(code);
    }
}
