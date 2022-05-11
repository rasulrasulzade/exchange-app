package com.company.exchangeapp.service;

import com.company.exchangeapp.entity.RequestedCurrency;
import com.company.exchangeapp.inter.CurrencyCount;
import com.company.exchangeapp.model.RequestedCurrencyModel;
import com.company.exchangeapp.repository.RequestedCurrencyRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AuditService {
    private final RequestedCurrencyRepository requestedCurrencyRepository;

    public AuditService(RequestedCurrencyRepository requestedCurrencyRepository) {
        this.requestedCurrencyRepository = requestedCurrencyRepository;
    }

    public void saveCurrency(Date date, String currency) {
        RequestedCurrency requestedCurrency = new RequestedCurrency(date, currency);
        requestedCurrencyRepository.save(requestedCurrency);
    }

    public RequestedCurrencyModel findRequestedCurrencies(Date date) {
        Set<CurrencyCount> set = requestedCurrencyRepository.findByDate(date);

        Map<String, Integer> map = new HashMap<>();
        for (CurrencyCount c : set) {
            map.put(c.getCurrency(), c.getRequestCount());
        }

        RequestedCurrencyModel currencyModel = new RequestedCurrencyModel();
        currencyModel.setDate(date);
        currencyModel.setCurrencies(map);

        return currencyModel;
    }
}
