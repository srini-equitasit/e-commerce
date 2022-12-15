package com.equitasit.product_price_service.controller;

import com.equitasit.product_price_service.dto.ProductPriceDTO;
import com.equitasit.product_price_service.service.ProductPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productPrice")
@Slf4j
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;

    @PostMapping
    public ResponseEntity save(@RequestBody ProductPriceDTO productPriceDTO) {

        log.debug("enter");
        log.info("saving the product price info {}", productPriceDTO);
        ProductPriceDTO savedProductPriceDTO = productPriceService.save(productPriceDTO);
        log.info("saved  price info {}", savedProductPriceDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductPriceDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProductPriceDTO productPriceDTO) {
        log.debug("enter");
        log.info("updating the product price info {}", productPriceDTO);
        ProductPriceDTO savedProductPriceDTO = productPriceService.update(productPriceDTO);
        log.info("updated product info {}", savedProductPriceDTO);
        log.debug("exit");
        return ResponseEntity.ok(savedProductPriceDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer productPriceId) {
        log.debug("enter");
        log.info("getting product price info for id {}", productPriceId);
        ProductPriceDTO productPriceDTO = productPriceService.get(productPriceId);
        log.info("product price info {}", productPriceDTO);
        log.debug("exit");
        return ResponseEntity.ok(productPriceDTO);
    }

    @GetMapping("product/{id}")
    public ResponseEntity getPriceForProduct(@PathVariable("id") Integer productId) {
        log.debug("enter");
        log.info("getting product price info for product id {}", productId);
        ProductPriceDTO productPriceDTO = productPriceService.getProductPriceForProdId(productId);
        log.info("product price info {}", productPriceDTO);
        log.debug("exit");
        return productPriceDTO != null ? ResponseEntity.ok(productPriceDTO) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer productPriceId) {

        log.debug("enter");
        log.info("removing product price info for id {}", productPriceId);

        productPriceService.remove(productPriceId);

        log.info("getting product price info for id {}", productPriceId);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity get() {
        log.debug("enter");

        List<ProductPriceDTO> productPriceDTOList = productPriceService.getAll();

        log.info("product price size {}", productPriceDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(productPriceDTOList);
    }
}
