package com.wiryaimd.r_services2currencyexchange.controller;

import com.wiryaimd.r_services2currencyexchange.model.CurrencyExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchangeModel> get1(@PathVariable String from, @PathVariable String to){
        CurrencyExchangeModel currencyExchangeModel = new CurrencyExchangeModel(1001L, from, to, BigDecimal.valueOf(60));
        currencyExchangeModel.setEnvironment(environment.getProperty("local.server.port")); // bisa juga ditambah local. mungkin biar ngga bentrok ama ntar dari importan confignya

        System.out.println("env port: " + currencyExchangeModel.getEnvironment());

        return ResponseEntity.ok(currencyExchangeModel);
    }

}
