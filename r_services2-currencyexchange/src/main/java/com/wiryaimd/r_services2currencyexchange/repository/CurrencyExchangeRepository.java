package com.wiryaimd.r_services2currencyexchange.repository;

import com.wiryaimd.r_services2currencyexchange.model.CurrencyExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeModel, Long> {

    Optional<CurrencyExchangeModel> findByFromAndTo(String from, String to);

}
