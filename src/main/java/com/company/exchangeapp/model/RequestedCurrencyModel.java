package com.company.exchangeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestedCurrencyModel {
    private Date date;
    private Map<String, Integer> currencies;
}
