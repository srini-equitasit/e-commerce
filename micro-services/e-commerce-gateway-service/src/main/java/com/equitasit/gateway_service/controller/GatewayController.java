package com.equitasit.gateway_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class GatewayController {

    @GetMapping("/{name}/fallback")
    Mono<ResponseEntity> getProductFallback(@PathVariable("name") String name) {
        log.info("fall backing for {} ", name);
        ResponseEntity responseEntity = ResponseEntity.noContent().build();
        return Mono.just(responseEntity);
    }


}
