package com.equitasit.cart_service.controller;

import com.equitasit.cart_service.dto.StatusDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CartExceptionHandler {



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<StatusDTO> handleException(Exception ex) {

        log.error("Error while accessing the products information", ex);

        return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
