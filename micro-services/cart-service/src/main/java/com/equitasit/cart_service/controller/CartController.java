package com.equitasit.cart_service.controller;

import com.equitasit.cart_service.dto.CartDTO;
import com.equitasit.cart_service.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity save(@RequestBody CartDTO cartDTO) {

        log.debug("enter");
        log.info("saving the cart info {}", cartDTO);
        CartDTO savedCartDTO = cartService.save(cartDTO);
        log.info("saved cart info {}", savedCartDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCartDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        log.debug("enter");
        log.info("getting cart info for id {}", id);
        CartDTO cartDTO = cartService.get(id);
        log.info("cart info {}", cartDTO);
        log.debug("exit");
        return ResponseEntity.ok(cartDTO);
    }

}
