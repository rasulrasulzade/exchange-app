package com.company.exchangeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeModel {
    private String from;
    private String to;
    private BigDecimal exchange;
}
