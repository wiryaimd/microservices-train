package com.wiryaimd.r_services3currencyconversion.controller;

import com.wiryaimd.r_services3currencyconversion.model.CurrencyConversionModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class CurrencyConversionController {

    private Environment environment;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionModel> calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);

        ResponseEntity<CurrencyConversionModel> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8002/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionModel.class,
                map
        );
        CurrencyConversionModel currencyConversionModel = responseEntity.getBody();

        if (currencyConversionModel == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CurrencyConversionModel(currencyConversionModel.getId(), from, to, currencyConversionModel.getConversionMultiple(), quantity, quantity.multiply(currencyConversionModel.getConversionMultiple()), currencyConversionModel.getEnvironment()));
    }

}
