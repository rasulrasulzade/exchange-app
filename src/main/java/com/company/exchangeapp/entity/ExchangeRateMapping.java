package com.company.exchangeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="exchange_rate_mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateMapping implements Serializable {
    @Id
    @Column(name="exchange_rate_id")
    private Long exchangeRateId;

    @Id
    private String currency;

    private BigDecimal rate;
}
