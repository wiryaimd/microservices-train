package com.wiryaimd.r_apigateway1.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

// fungsi config untuk gateway ini akan mendefine endpoint mana yg perlu di redirect ke services tertentu
//  eg ketika endpoint /get di request, maka config ini akan ngeredirect endpointnya ke target endpoint/service seperti jsonplaceholder.c/todos/1
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                // jadi route ini akan ngeredirect ke json placeholder ketika /get dipanggil
                .route(new Function<PredicateSpec, Buildable<Route>>() {
                    @Override
                    public Buildable<Route> apply(PredicateSpec predicateSpec) {
                        return predicateSpec
                                .path("/get")
                                .filters(
                                        f -> f.addRequestHeader("TestHeader1", "headerData1") // bisa juga ditambahkan dengan header pada endpoint ini
                                                .addRequestParameter("param1", "param-val1") // dan bisa ditambahkan parameter
                                )
                                .uri("http://httpbin.org/");
                    }
                }).route(
                        // jadi kalo pakai regex dan ngetarget load balancer, path ini perlu sama dgn endpoint target service nya, jadi bisa di set /currency-exchange/** dengan target services curr-exchange, nanti itu akan ngecall ke service target dgn path yg sama dari gatewaynya
                        p -> p.path("/currency-exchange/**") // ketika endpoint ini di req dari service api gateway, maka
                                .uri("lb://services2-currencyexchange-app")
                                // akan mengredirect ke endpoint ini, dimana key lb: akan mengaktifkan load balancer,
                                // serta diikuti nama service yang telah terdaftar pada naming servernya / eureka
                ).route(
                        p -> p.path("/currency-conversion/**")
                                .uri("lb://services3-currency-conversion1")
                ).route(
                        p -> p.path("/currency-conversion-feign/**")
                                .uri("lb://services3-currency-conversion1")
                ).route(
                        p -> p.path("/currency-conversion-new/**")
                                // bisa juga pakai filters, rewrite path, untuk mengarahkan endpoint conversion-new dan
                                // meredirect ke endpoint conversion-feign, jadi kaya bkin custom endpoint gitu untuk spesifik target pada endpoint/service
                                .filters(f -> f.rewritePath( // rewritePath() berisi 2 param string, dimana param1 string mana yg akan di replace, lalu param2 direplace menjadi apa string tsb
                                        "/currency-conversion-new/(?<segment>.*)", // berisi string yg akan di replace yaitu conversion-new dan ada regex (?<segment>.*) agar path /** di jadikan sebagai variabel
                                        "/currency-conversion-feign/${segment}") // berisi string yg akan mereplace string diatas, dengan custom variable ${segment} untuk /** nya
                                )
                                .uri("lb://services3-currency-conversion1")
                                // temp note
                                // ex request ke endpoint ini http://localhost:8765/currency-conversion-new/from/idr/to/usd/quantity/253
                                // akan mereplace path conversion-new dan merequestnya ke http://localhost:8765/currency-conversion-feign/from/idr/to/usd/quantity/253
                )
                .build();
    }

}
