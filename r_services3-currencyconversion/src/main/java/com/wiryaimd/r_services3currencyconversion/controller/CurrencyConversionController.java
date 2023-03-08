package com.wiryaimd.r_services3currencyconversion.controller;

import com.wiryaimd.r_services3currencyconversion.model.CurrencyConversionModel;
import com.wiryaimd.r_services3currencyconversion.proxy.CurrencyExchangeProxy;
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

    private CurrencyExchangeProxy currencyExchangeProxy;
    private Environment environment;


    // call api service, menggunakan RestTemplate
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionModel> calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);

        ResponseEntity<CurrencyConversionModel> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8001/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionModel.class,
                map
        );
        CurrencyConversionModel currencyConversionModel = responseEntity.getBody();

        if (currencyConversionModel == null){
            return ResponseEntity.notFound().build();
        }


        log.info("conversion feign dgn base port: " + environment.getProperty("server.port"));

        return ResponseEntity.ok(new CurrencyConversionModel(currencyConversionModel.getId(), from, to, currencyConversionModel.getConversionMultiple(), quantity, quantity.multiply(currencyConversionModel.getConversionMultiple()), currencyConversionModel.getEnvironment() + "-restTemplate"));
    }

    // call api service, menggunakan feign
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionModel> calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        // request nya akan otomatis nge load balance karena sudah menggunakan feign
        // port pada responsenya pun akan berupa 8001 atau 8002 etc jika kedua instance UP
        // jika tidak muncul, tunggu katanya 15-30 detik untuk eurekanya ngeload balance

        // katanya ini dinamankan client-side load balance
        CurrencyConversionModel currencyConversionModel = currencyExchangeProxy.currencyExchange1(from, to);

        return ResponseEntity.ok(new CurrencyConversionModel(currencyConversionModel.getId(), from, to, currencyConversionModel.getConversionMultiple(), quantity, quantity.multiply(currencyConversionModel.getConversionMultiple()), currencyConversionModel.getEnvironment() + "-Feign"));
    }

}
