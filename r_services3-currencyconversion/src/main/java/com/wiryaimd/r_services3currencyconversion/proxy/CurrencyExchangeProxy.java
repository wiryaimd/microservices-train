package com.wiryaimd.r_services3currencyconversion.proxy;

import com.wiryaimd.r_services3currencyconversion.model.CurrencyConversionModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient katanya harus tipe interface, karena emg gaperlu pake body apa2
// param name adalah spring.application.name dari service yg ingin di call reqnya, (app.name yg legal itu tanpa isi underscore, kalo isi ntar error)
// lalu dengan param url nya yaitu base urlnya, tanpa berisi path

// note
// ketika menggunakan feign serta naming server/eureka bersama maka
// feign akan otomatis ngerequest instance nya ke eureka, bukan langsung/direct ke server tsb
// dan feign ini akan ngeload balance melalui request ke eureka

//@FeignClient(name = "services2-currencyexchange-app", url = "localhost:8001")
@FeignClient(name = "services2-currencyexchange-app") // jadi gaperlu pake param url, karena udh di urus ama eureka
public interface CurrencyExchangeProxy {

    // path yg bakal di request kesini .. currency-exchange
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionModel currencyExchange1(@PathVariable String from, @PathVariable String to);
    // mereturn CurrencyConversionModel dan akan menyesuaikan isi attr response ke isi model (sama seperti responsenya RestTemplate)
    // jika attr yg di response tidak terdefine di model, maka tidak akan di set datanya

}
