package com.kb.home.controller;

import com.kb.home.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public String getExchangeRate(
            @RequestParam(required = false, defaultValue = "VND,KRW") String symbols,
            @RequestParam(required = false, defaultValue = "USD") String base) {
        return exchangeRateService.getExchangeRate(symbols, base);
    }
}
