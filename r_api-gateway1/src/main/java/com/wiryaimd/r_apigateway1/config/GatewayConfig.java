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
                        p -> p.path("/currency-ex/**") // ketika endpoint ini di req dari service api gateway, maka
                                .uri("lb:services2-currencyexchange-app")
                                // akan mengredirect ke endpoint ini, dimana key lb: akan mengaktifkan load balancer,
                                // serta diikuti nama service yang telah terdaftar pada naming servernya / eureka
                )
                .build();
    }

}
