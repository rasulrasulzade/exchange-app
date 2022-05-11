package com.company.exchangeapp.service;

import com.company.exchangeapp.entity.ExchangeRate;
import com.company.exchangeapp.entity.ExchangeRateMapping;
import com.company.exchangeapp.entity.ExchangeSpread;
import com.company.exchangeapp.exception.CustomException;
import com.company.exchangeapp.model.ExchangeModel;
import com.company.exchangeapp.repository.ExchangeRateRepository;
import com.company.exchangeapp.repository.ExchangeSpreadRepository;
import com.company.exchangeapp.util.ExchangeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ExchangeService {

    @Value("${app.apikey}")
    private String apiKey;

    @Value("${app.rateUrl}")
    private String rateUrl;

    @Value("${app.baseCurrency}")
    private String baseCurrency;

    @Value("${app.defaultSpreadPercentage}")
    private BigDecimal defaultSpreadPercentage;

    private final WebClient.Builder webClientBuilder;

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeUtil exchangeUtil;

    private final ExchangeSpreadRepository exchangeSpreadRepository;

    private final AuditService auditService;

    public ExchangeService(WebClient.Builder webClientBuilder, ExchangeRateRepository exchangeRateRepository, ExchangeUtil exchangeUtil, ExchangeSpreadRepository exchangeSpreadRepository, AuditService auditService) {
        this.webClientBuilder = webClientBuilder;
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeUtil = exchangeUtil;
        this.exchangeSpreadRepository = exchangeSpreadRepository;
        this.auditService = auditService;
    }

    @Scheduled(cron = "0 5 00 * * ?", zone = "GMT")
    public void fetchExchangeRates() {
        ExchangeRate exchangeRate = webClientBuilder.build()
                .get()
                .uri(rateUrl)
                .header("apiKey", apiKey)
                .retrieve()
                .bodyToMono(ExchangeRate.class)
                .block();

        exchangeRateRepository.save(exchangeRate);
    }

    public ExchangeModel findExchangeRate(Optional<String> date, String fromCurrency, String toCurrency) {
        Set<ExchangeRateMapping> rates;
        if (date.isPresent()) {
            rates = exchangeRateRepository.findByBaseAndDate(baseCurrency, java.sql.Date.valueOf(date.get()), fromCurrency, toCurrency);
        } else {
            rates = exchangeRateRepository.findByBaseAndCurrency(baseCurrency, fromCurrency, toCurrency);
        }

        if (rates.size() == 0) {
            throw new CustomException("Not found!", HttpStatus.NOT_FOUND);
        }

        //save currencies to audit table
        auditService.saveCurrency(new java.sql.Date(System.currentTimeMillis()), fromCurrency);
        auditService.saveCurrency(new java.sql.Date(System.currentTimeMillis()), toCurrency);

        Map<String, BigDecimal> rateMap = new HashMap<>();
        for (ExchangeRateMapping ob : rates) {
            rateMap.put(ob.getCurrency(), ob.getRate());
        }
        BigDecimal fromRate = rateMap.get(fromCurrency);
        BigDecimal toRate = rateMap.get(toCurrency);

        ExchangeSpread fromSpread = exchangeSpreadRepository.findByCurrency(fromCurrency);
        ExchangeSpread toSpread = exchangeSpreadRepository.findByCurrency(toCurrency);

        BigDecimal fromSpreadPercentage = fromSpread != null ? fromSpread.getPercentage() : defaultSpreadPercentage;
        BigDecimal toSpreadPercentage = toSpread != null ? toSpread.getPercentage() : defaultSpreadPercentage;

        BigDecimal rate = exchangeUtil.calculateExchangeRate(fromRate, toRate, fromSpreadPercentage, toSpreadPercentage);

        return new ExchangeModel(fromCurrency, toCurrency, rate);
    }
}
