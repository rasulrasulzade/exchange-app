package com.company.exchangeapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="requested_currency")
@Data
public class RequestedCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private String currency;

    public RequestedCurrency() {
    }

    public RequestedCurrency(Date date, String currency) {
        this.date = date;
        this.currency = currency;
    }
}
