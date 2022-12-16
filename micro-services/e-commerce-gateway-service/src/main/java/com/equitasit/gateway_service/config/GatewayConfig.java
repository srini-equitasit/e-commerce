package com.equitasit.gateway_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GatewayConfig {

    @Autowired
    private ECommerceAppConfig ecommerceAppConfig;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        log.info("App Config  {} ", ecommerceAppConfig);
        return builder.routes()
                .route(p -> p
                        .path("/productPrice/**")
                        .filters(rw -> rw.rewritePath("/productPrice/(?<segment>.*)", "/productPrice/${segment}"))
                        .uri(ecommerceAppConfig.getPriceUrl()))
                .route(p -> p.path("/productSeller/**")
                        .filters(rw -> rw.rewritePath("/productSeller/(?<segment>.*)", "/productSeller/${segment}"))
                        .uri(ecommerceAppConfig.getSellerUrl()))
                .route(p -> p.path("/products/**")
                        .filters(rw -> rw.rewritePath("/products/(?<segment>.*)", "/products/${segment}"))
                        .uri(ecommerceAppConfig.getProductUrl()))
                .build();
    }
}
