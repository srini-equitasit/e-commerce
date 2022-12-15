package com.equitasit.product_service.controller;

import com.equitasit.product_service.dto.StatusDTO;
import com.equitasit.product_service.exception.ProductException;
import com.equitasit.product_service.util.MsgConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ProductExceptionHandler {

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<StatusDTO> handleProductException(ProductException ex) {

        log.error("Error while getting the products", ex);

        if (MsgConstants.PRODUCT_NOT_FOUND == ex.getMsgConstant()) {
            return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<StatusDTO> handleException(Exception ex) {

        log.error("Error while accessing the products information", ex);

        return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
