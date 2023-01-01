package com.equitasit.gateway_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

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
                        .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(getBackoffConfig()))
                                .circuitBreaker(cb -> cb.setName("price").setFallbackUri("forward:/price/fallback")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                        .uri(ecommerceAppConfig.getPriceUrl()))
                .route(p -> p.path("/seller/**")
                        .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(getBackoffConfig()))
                                .circuitBreaker(cb -> cb.setName("seller").setFallbackUri("forward:/seller/fallback")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                        .uri(ecommerceAppConfig.getSellerUrl()))

                .route(p -> p.path("/product/**")
                        .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(getBackoffConfig()))
                                .circuitBreaker(cb -> cb.setName("product")
                                        .setFallbackUri("forward:/product/fallback")
                                ))
                        .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                        .uri(ecommerceAppConfig.getProductUrl()))
                .route(p -> p.path("/user/**")

                        .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(getBackoffConfig()))
                                .circuitBreaker(cb -> cb.setName("user")
                                        .setFallbackUri("forward:/user/fallback")
                                ))
                        .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                        .uri(ecommerceAppConfig.getUserUrl()))
                .build();
        log.info("routes configure end , routeLocator {} ", routeLocator);
        return routeLocator;
    }

    private RetryGatewayFilterFactory.BackoffConfig getBackoffConfig() {
        RetryGatewayFilterFactory.BackoffConfig backoffConfig = new RetryGatewayFilterFactory.BackoffConfig();
        backoffConfig.setFirstBackoff(Duration.ofMillis(500));
        backoffConfig.setMaxBackoff(Duration.ofMillis(1000));
        return backoffConfig;
    }


}
