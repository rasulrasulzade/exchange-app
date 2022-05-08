package com.company.exchangeapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="exchange_spread")
@Data
public class ExchangeSpread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;

    private BigDecimal percentage;
}
