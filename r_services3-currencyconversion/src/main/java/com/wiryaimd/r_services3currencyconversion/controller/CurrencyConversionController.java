package com.wiryaimd.r_services3currencyconversion.controller;

import com.wiryaimd.r_services3currencyconversion.model.CurrencyConversionModel;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    private Environment environment;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionModel> calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        return ResponseEntity.ok(new CurrencyConversionModel(1000L, from, to, BigDecimal.ONE, quantity, BigDecimal.TEN, environment.getProperty("local.server.port")));
    }

}
