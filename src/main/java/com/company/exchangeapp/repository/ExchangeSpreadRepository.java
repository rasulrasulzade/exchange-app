package com.company.exchangeapp.repository;

import com.company.exchangeapp.entity.ExchangeSpread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeSpreadRepository extends JpaRepository<ExchangeSpread, Long> {
    ExchangeSpread findByCurrency(String currency);
}
