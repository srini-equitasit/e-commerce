package com.equitasit.cart_service.controller;

import com.equitasit.cart_service.dto.CartDTO;
import com.equitasit.cart_service.dto.CartItemDTO;
import com.equitasit.cart_service.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("{id}")
    public ResponseEntity save(@PathVariable("id") Integer userId, @RequestBody CartItemDTO cartDTO) {

        log.debug("enter");
        log.info("saving the cart info {}", cartDTO);
        List<CartItemDTO> savedCartDTOList = cartService.save(userId, cartDTO);
        log.info("saved cart info {}", savedCartDTOList);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCartDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer userId) {
        log.debug("enter");
        log.info("getting cart info for userId {}", userId);
        List<CartItemDTO> savedCartDTOList = cartService.get(userId);
        log.info("cart info {}", savedCartDTOList);
        log.debug("exit");
        return ResponseEntity.ok(savedCartDTOList);
    }

    @GetMapping("{id}/count")
    public ResponseEntity getCartCount(@PathVariable("id") Integer userId) {
        log.debug("enter");
        CartDTO cartDTO = cartService.getCartCount(userId);

        log.info("cartDTO {} ", cartDTO);
        log.debug("exit");
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer userId) {
        log.debug("enter");
        log.info("removing cart info for userId {}", userId);
        cartService.remove(userId);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity removeProduct(@RequestBody CartItemDTO cartItemDTO) {
        log.debug("enter");
        log.info("removing cart info for userId {}", cartItemDTO);
        cartService.remove(cartItemDTO.getUserId(), cartItemDTO.getProductId());
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

}
