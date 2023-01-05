package com.equitasit.gateway_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

@Configuration
@Slf4j
public class GatewayRoutesConfig {

    @Autowired
    private ECommerceAppConfig ecommerceAppConfig;

    Map<String, String> urlToPartMap = new HashMap<>();

    Buildable<Route> getRouteConfig(PredicateSpec p, String basePath, String targetUrl) {
        return p
                .path("/" + basePath + "/**")
                .filters(f -> f.retry(rc -> rc.allMethods().setRetries(3).setBackoff(getBackoffConfig()))
                        .circuitBreaker(cb -> cb.setName(basePath).setFallbackUri("forward:/" + basePath + "/fallback")))
                .metadata(CONNECT_TIMEOUT_ATTR, 2000)
                .metadata(RESPONSE_TIMEOUT_ATTR, 3000)
                .uri(targetUrl);

    }


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        log.info("routes configure start");
        log.info("App Config  {} ", ecommerceAppConfig);
        RouteLocator routeLocator = builder.routes()
                .route(p -> getRouteConfig(p, "price", ecommerceAppConfig.getPriceUrl()))
                .route(p -> getRouteConfig(p, "seller", ecommerceAppConfig.getSellerUrl()))
                .route(p -> getRouteConfig(p, "product", ecommerceAppConfig.getProductUrl()))

                .route(p -> getRouteConfig(p, "user", ecommerceAppConfig.getUserUrl()))
                .route(p -> getRouteConfig(p, "cart", ecommerceAppConfig.getCartItemsUrl()))


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
