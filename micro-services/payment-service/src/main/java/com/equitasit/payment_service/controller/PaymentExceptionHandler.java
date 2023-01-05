package com.equitasit.payment_service.controller;

import com.equitasit.payment_service.dto.StatusDTO;
import com.equitasit.payment_service.exception.PaymentException;
import com.equitasit.payment_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class PaymentExceptionHandler {

    @ExceptionHandler(value = PaymentException.class)
    public ResponseEntity<StatusDTO> handleProductException(PaymentException ex) {

        log.error("Error while getting the users ", ex);

        if (MsgConstants.ACT_NOT_FOUND == ex.getMsgConstant()) {
            return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<StatusDTO> handleException(Exception ex) {

        log.error("Error while accessing the users information", ex);

        return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
