package com.equitasit.gateway_service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
                        .filters(rw -> rw.rewritePath("/price/(?<segment>.*)", getPath(ecommerceAppConfig.getPriceUrl())))
                        .uri(ecommerceAppConfig.getPriceUrl()))
                .route(p -> p.path("/seller/**")
                        .filters(rw -> rw.rewritePath("/seller/(?<segment>.*)", getPath(ecommerceAppConfig.getSellerUrl())))
                        .uri(ecommerceAppConfig.getSellerUrl()))

                .route(p -> p.path("/products/**")
                        .filters(rw -> rw.rewritePath("/products/(?<segment>.*)", getPath(ecommerceAppConfig.getProductUrl())))
                        .uri(ecommerceAppConfig.getProductUrl()))
                .build();
        log.info("routes configure end , routeLocator {} ", routeLocator);
        return routeLocator;
    }

    private String getPath(String urlStr) {
        String part = null;
        log.debug("enter");
        try {
            if (urlToPartMap.containsKey(urlStr)) {
                part = urlToPartMap.get(urlStr);
            } else {
                log.info("urlStr {} ", urlStr);
                URL url = new URL(urlStr);
                log.info("url {} ", url);
                part = "/" + url.getPath().split("/")[1] + "/${segment}";
                log.info("part {} ", part);
                urlToPartMap.put(urlStr, part);
            }
        } catch (Exception e) {

        }
        log.debug("part  {} ", part);
        log.debug("exit");
        return part;
    }


}
