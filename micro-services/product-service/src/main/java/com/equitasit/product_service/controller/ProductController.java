package com.equitasit.product_service.controller;

import com.equitasit.product_service.dto.ProductDTO;
import com.equitasit.product_service.dto.StatusDTO;
import com.equitasit.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity save(@RequestBody ProductDTO productDTO) {

        log.debug("enter");
        log.info("saving the product info {}", productDTO);
        ProductDTO savedProductDTO = productService.save(productDTO);
        log.info("saved product info {}", productDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProductDTO productDTO) {
        log.debug("enter");
        log.info("updating the product info {}", productDTO);
        ProductDTO savedProductDTO = productService.update(productDTO);
        log.info("updated product info {}", productDTO);
        log.debug("exit");
        return ResponseEntity.ok(savedProductDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer productId) {
        log.debug("enter");
        log.info("getting product info for id {}", productId);
        ProductDTO productDTO = productService.get(productId);
        log.info("product info {}", productDTO);
        log.debug("exit");
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer productId) {

        log.debug("enter");
        log.info("removing product info for id {}", productId);

        productService.remove(productId);

        log.info("getting product info for id {}", productId);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity get() {
        log.debug("enter");

        List<ProductDTO> productDTOList = productService.getAll();

        log.info("product size {}", productDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(productDTOList);
    }

}
