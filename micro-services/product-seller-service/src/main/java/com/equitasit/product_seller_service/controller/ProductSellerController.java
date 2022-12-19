package com.equitasit.product_seller_service.controller;

import com.equitasit.product_seller_service.dto.ProductSellerDTO;
import com.equitasit.product_seller_service.service.ProductSellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seller")
@Slf4j
public class ProductSellerController {

    @Autowired
    private ProductSellerService productSellerService;

    @PostMapping
    public ResponseEntity save(@RequestBody ProductSellerDTO productSellerDTO) {

        log.debug("enter");
        log.info("saving the product seller info {}", productSellerDTO);
        ProductSellerDTO savedProductSellerDTO = productSellerService.save(productSellerDTO);
        log.info("saved product seller info {}", productSellerDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductSellerDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProductSellerDTO productSellerDTO) {
        log.debug("enter");
        log.info("updating the product seller info {}", productSellerDTO);
        ProductSellerDTO savedProductSellerDTO = productSellerService.update(productSellerDTO);
        log.info("updated product info {}", savedProductSellerDTO);
        log.debug("exit");
        return ResponseEntity.ok(savedProductSellerDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer productSellerId) {
        log.debug("enter");
        log.info("getting product seller info for id {}", productSellerId);
        ProductSellerDTO productSellerDTO = productSellerService.get(productSellerId);
        log.info("product seller info {}", productSellerDTO);
        log.debug("exit");
        return ResponseEntity.ok(productSellerDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer productSellerId) {

        log.debug("enter");
        log.info("removing product seller info for id {}", productSellerId);

        productSellerService.remove(productSellerId);

        log.info("getting product seller info for id {}", productSellerId);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity get() {
        log.debug("enter");

        List<ProductSellerDTO> productSellerDTOList = productSellerService.getAll();

        log.info("product size {}", productSellerDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(productSellerDTOList);
    }

    @GetMapping("product/{id}")
    public ResponseEntity getSellersForProductId(@PathVariable("id") Integer productId) {
        log.debug("enter");
        log.info("getting product seller info for product id {}", productId);
        List<ProductSellerDTO> productSellerDTOList = productSellerService.getSellersForProductId(productId);
        log.info("product seller info list {}", productSellerDTOList);
        log.debug("exit");
        return ResponseEntity.ok(productSellerDTOList);
    }


}
