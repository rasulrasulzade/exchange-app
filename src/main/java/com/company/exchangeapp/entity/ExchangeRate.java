package com.company.exchangeapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name="exchange_rate")
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Boolean success;

    private String base;

    private Date date;

    private Timestamp timestamp;

    @ElementCollection
    @CollectionTable(name = "exchange_rate_mapping",
            joinColumns = {@JoinColumn(name = "exchange_rate_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "currency")
    @Column(name = "rate")
    private Map<String, BigDecimal> rates;

}
