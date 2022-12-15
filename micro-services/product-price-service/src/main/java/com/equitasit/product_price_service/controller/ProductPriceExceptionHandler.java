package com.equitasit.product_price_service.controller;

import com.equitasit.product_price_service.dto.StatusDTO;
import com.equitasit.product_price_service.exception.ProductPriceException;
import com.equitasit.product_price_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ProductPriceExceptionHandler {

    @ExceptionHandler(value = ProductPriceException.class)
    public ResponseEntity<StatusDTO> handleProductException(ProductPriceException ex) {

        log.error("Error while getting the product price", ex);

        if (MsgConstants.PRODUCT_PRICE_NOT_FOUND == ex.getMsgConstant()) {
            return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<StatusDTO> handleException(Exception ex) {

        log.error("Error while accessing the products price information", ex);

        return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
