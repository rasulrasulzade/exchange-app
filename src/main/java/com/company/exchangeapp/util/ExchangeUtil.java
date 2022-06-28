package com.company.exchangeapp.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ExchangeUtil {
    public BigDecimal calculateExchangeRate(BigDecimal fromRate, BigDecimal toRate, BigDecimal fromSpread, BigDecimal toSpread) {
        BigDecimal part1 = toRate.divide(fromRate, 10, RoundingMode.HALF_UP);
        BigDecimal part2 = BigDecimal.valueOf(100).subtract(toSpread.max(fromSpread)).divide(BigDecimal.valueOf(100));
        return part1.multiply(part2);
    }
}
