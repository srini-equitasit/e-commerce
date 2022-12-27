package com.equitasit.gateway_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController {

    @GetMapping("/product/fallback")
    Mono<ResponseEntity> getProductFallback() {
        ResponseEntity responseEntity = ResponseEntity.noContent().build();
        return Mono.just(responseEntity);
    }
    @GetMapping("/seller/fallback")
    Mono<ResponseEntity> getSellerFallback() {
        ResponseEntity responseEntity = ResponseEntity.noContent().build();
        return Mono.just(responseEntity);
    }
    @GetMapping("/price/fallback")
    Mono<ResponseEntity> getPriceFallback() {
        ResponseEntity responseEntity = ResponseEntity.noContent().build();
        return Mono.just(responseEntity);
    }
}
