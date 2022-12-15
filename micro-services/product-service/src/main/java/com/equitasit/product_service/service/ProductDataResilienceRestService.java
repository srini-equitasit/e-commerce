package com.equitasit.product_service.service;

import com.equitasit.product_service.config.ProductAppConfig;
import com.equitasit.product_service.dto.ProductPriceDTO;
import com.equitasit.product_service.dto.ProductSellerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ProductDataResilienceRestService {

    private RestTemplate rest;
    private CircuitBreakerFactory cbFactory;

    private ProductAppConfig productAppConfig;


    public ProductDataResilienceRestService(RestTemplate rest, CircuitBreakerFactory cbFactory, ProductAppConfig productAppConfig) {
        this.rest = rest;
        this.cbFactory = cbFactory;
        this.productAppConfig = productAppConfig;
    }

    public ProductPriceDTO getProductPrice(final Integer productId) {
        CircuitBreaker circuitBreaker = getCircuitBreaker("productPrice");

        return circuitBreaker.run(() -> invokeProductPrice(productId), this::productPriceFallback);
    }



    public List<ProductSellerDTO> getSellersInfo(final Integer productId) {
        CircuitBreaker circuitBreaker = getCircuitBreaker("productPrice");
        return circuitBreaker.run(() -> {
            return this.invokeSellersInfo(productId);
        }, this::productSellerFallback);
    }



    private CircuitBreaker getCircuitBreaker(String name) {

        CircuitBreaker circuitBreaker = cbFactory.create("productPrice");

        return circuitBreaker;
    }


    private ProductPriceDTO invokeProductPrice(final Integer productId) {
        log.debug("enter");
        String finalUrl = productAppConfig.getPriceUrl() + "/" + productId;
        log.info("invoking price url {} ", finalUrl);
        ProductPriceDTO productPriceDTO = rest.getForObject(finalUrl, ProductPriceDTO.class);
        log.info("response, productPriceDTO {}", productPriceDTO);
        log.debug("exit");
        return productPriceDTO;
    }

    private List<ProductSellerDTO> invokeSellersInfo(final Integer productId) {
        log.debug("enter");
        String finalUrl = productAppConfig.getSellerUrl() + "/" + productId;
        log.info("invoking seller url {} ", finalUrl);

        ResponseEntity<List<ProductSellerDTO>> responseEntity =
                rest.exchange(finalUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductSellerDTO>>() {
                });
        log.info("seller response  = {} ", responseEntity);
        log.debug("exit");
        return responseEntity.getBody();
    }

    private ProductPriceDTO productPriceFallback(Throwable t) {
        log.error("error while invoking product api", t);
        log.warn("invoked fallback method and returning the empty response");
        return new ProductPriceDTO();
    }

    private List<ProductSellerDTO> productSellerFallback(Throwable t) {
        log.error("error while invoking seller api", t);
        log.warn("invoked fallback method and returning the empty response");
        return Collections.EMPTY_LIST;
    }

}
