package com.equitasit.gateway_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

@Configuration
@Slf4j
public class GatewayRoutesConfig {

    @Autowired
    private ECommerceAppConfig ecommerceAppConfig;

    Map<String, String> urlToPartMap = new HashMap<>();

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        log.info("routes configure start");
        log.info("App Config  {} ", ecommerceAppConfig);
        RouteLocator routeLocator = builder.routes()
                .route(p -> p
                        .path("/price/**")
                        .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(Duration.ofMillis(50), Duration.ofMillis(500), 2, true))
                                .circuitBreaker(cb -> cb.setName("price")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                        .uri(ecommerceAppConfig.getPriceUrl()))
                .route(p -> p.path("/seller/**")
                        .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(Duration.ofMillis(50), Duration.ofMillis(500), 2, true))
                                .circuitBreaker(cb -> cb.setName("seller")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                        .uri(ecommerceAppConfig.getSellerUrl()))

                .route(p -> p.path("/product/**")
                        .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(Duration.ofMillis(50), Duration.ofMillis(500), 2, true))
                                .circuitBreaker(cb -> cb.setName("product")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                        .uri(ecommerceAppConfig.getProductUrl()))
                .build();
        log.info("routes configure end , routeLocator {} ", routeLocator);
        return routeLocator;
    }


}
