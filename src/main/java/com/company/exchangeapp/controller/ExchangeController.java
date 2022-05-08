package com.company.exchangeapp.controller;

import com.company.exchangeapp.model.ExchangeModel;
import com.company.exchangeapp.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    protected final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public ResponseEntity<ExchangeModel> getExchange(@RequestParam String from, @RequestParam String to, @RequestParam Optional<String> date) {
        ExchangeModel exchangeModel = exchangeService.findExchangeRate(date, from, to);

        return ResponseEntity.ok().body(exchangeModel);
    }

}
