package com.equitasit.product_service.controller;

import com.equitasit.product_service.dto.ProductDTO;
import com.equitasit.product_service.dto.ProductDataDTO;
import com.equitasit.product_service.service.ProductDataService;
import com.equitasit.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@Slf4j
public class ProductDataController {

    @Autowired
    private ProductDataService productDataService;

    @GetMapping("data/{id}")
    public ResponseEntity get(@PathVariable("id") Integer productId) throws Exception{
        log.debug("enter");
        log.info("getting product data for id {}", productId);
        ProductDataDTO productDataDTO = productDataService.getProductData(productId);
        log.info("product data {}", productDataDTO);
        log.debug("exit");
        return ResponseEntity.ok(productDataDTO);
    }
}
