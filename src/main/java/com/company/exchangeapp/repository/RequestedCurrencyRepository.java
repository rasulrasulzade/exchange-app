package com.company.exchangeapp.repository;

import com.company.exchangeapp.entity.RequestedCurrency;
import com.company.exchangeapp.inter.CurrencyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Set;

@Repository
public interface RequestedCurrencyRepository extends JpaRepository<RequestedCurrency, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(currency) AS requestCount, currency FROM requested_currency WHERE date=?1 GROUP BY currency")
    Set<CurrencyCount> findByDate(Date date);
}
