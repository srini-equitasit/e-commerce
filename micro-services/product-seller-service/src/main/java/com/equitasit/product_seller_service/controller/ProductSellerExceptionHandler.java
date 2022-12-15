package com.equitasit.product_seller_service.controller;

import com.equitasit.product_seller_service.dto.StatusDTO;
import com.equitasit.product_seller_service.exception.ProductSellerException;
import com.equitasit.product_seller_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ProductSellerExceptionHandler {

    @ExceptionHandler(value = ProductSellerException.class)
    public ResponseEntity<StatusDTO> handleProductException(ProductSellerException ex) {

        log.error("Error while getting the product sellers ", ex);

        if (MsgConstants.PRODUCT_SELLER_NOT_FOUND == ex.getMsgConstant()) {
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
