package com.company.exchangeapp.repository;

import com.company.exchangeapp.entity.ExchangeRate;
import com.company.exchangeapp.entity.ExchangeRateMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Set;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query("select erm \n" +
            "from ExchangeRate er\n" +
            "left join ExchangeRateMapping erm\n" +
            "on er.id=erm.exchangeRateId\n" +
            "where er.base = ?1 and (erm.currency = ?2 OR erm.currency = ?3) ORDER BY er.date")
    Set<ExchangeRateMapping> findByBaseAndCurrency(String base, String fromCurrency, String toCurrency);

    @Query("select erm \n" +
            "from ExchangeRate er\n" +
            "left join ExchangeRateMapping erm\n" +
            "on er.id=erm.exchangeRateId\n" +
            "where er.base = ?1 and er.date = ?2 and (erm.currency = ?3 OR erm.currency = ?4)")
    Set<ExchangeRateMapping> findByBaseAndDate(String base, Date date, String fromCurrency, String toCurrency);
}
