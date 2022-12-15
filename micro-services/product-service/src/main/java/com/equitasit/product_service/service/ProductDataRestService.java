package com.equitasit.product_service.service;

import com.equitasit.product_service.config.ProductAppConfig;
import com.equitasit.product_service.dto.ProductPriceDTO;
import com.equitasit.product_service.dto.ProductSellerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ProductDataRestService {

    private RestTemplate rest;
    private ProductAppConfig productAppConfig;


    public ProductDataRestService(RestTemplate rest, ProductAppConfig productAppConfig) {
        this.rest = rest;
        this.productAppConfig = productAppConfig;
    }

    public ProductPriceDTO getProductPrice(final Integer productId) {
        log.debug("enter");
        String finalUrl = productAppConfig.getPriceUrl() + "/" + productId;
        log.info("invoking price url {} ", finalUrl);
        ProductPriceDTO productPriceDTO = rest.getForObject(finalUrl, ProductPriceDTO.class);
        log.info("response, productPriceDTO {}", productPriceDTO);
        log.debug("exit");
        return productPriceDTO;
    }

    public List<ProductSellerDTO> getSellersInfo(final Integer productId) {
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


}
