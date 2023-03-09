package com.wiryaimd.r_services3currencyconversion.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.common.retry.configuration.CommonRetryConfigurationProperties;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {

    @GetMapping("/lul-hooh")
    // annotate dari resilience4j
    @Retry(name = "hooh-api1", fallbackMethod = "hardcodedFb1") // akan me retry / memanggil method ini sebanyak 3 kali (untuk name = "default"), kalau name = "custom-retry" bisa di set di properties config
    // annot retry juga berisi fallBack method, yg ketika error setelah retry beberapa kali, akan diarahkan ke method tertentu untuk diberikan response

//    @CircuitBreaker(name = "default", fallbackMethod = "cbFb1") // untuk membuat jeda / period time ketika call nya gagal terus, untuk beberapa detik
//    @RateLimiter(name = "default", fallbackMethod = "rlFb1") // memberikan rate limit, eg 10 detik hanya bisa 10.000 request/call. kalo udh lebih return too many request at same time
//    @Bulkhead(name = "default", fallbackMethod = "") // berapa banyak konkruen dalam suatu period
    public ResponseEntity<String> api1(){
        log.info("lul-hooh endpoint called");
        return new RestTemplate().getForEntity("http://localhost:8080/lul-endpoint", String.class); // dengan endpoint offline yg tidak akan berhasil di call
    }
    // dipakai untuk fallback method dari retry, perlu berisi param Exception, kalo ngga ya bakal err
    public ResponseEntity<String> hardcodedFb1(Exception e){ // dan juga return type perlu sama dengan api1() yaitu ResponseEntity<String>, kalo ngga bakal err NoSuchMethodException
        return ResponseEntity.status(404).body("take me homee alrightt belongg");
    }

}
