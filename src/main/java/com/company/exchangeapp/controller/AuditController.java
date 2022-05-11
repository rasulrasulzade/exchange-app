package com.company.exchangeapp.controller;

import com.company.exchangeapp.model.RequestedCurrencyModel;
import com.company.exchangeapp.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/currency")
    public ResponseEntity<RequestedCurrencyModel> getExchange(@RequestParam String date) {
        RequestedCurrencyModel model = auditService.findRequestedCurrencies(java.sql.Date.valueOf(date));

        return ResponseEntity.ok().body(model);
    }
}
