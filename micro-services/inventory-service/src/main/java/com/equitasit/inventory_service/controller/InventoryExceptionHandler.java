package com.equitasit.inventory_service.controller;


import com.equitasit.inventory_service.dto.StatusDTO;
import com.equitasit.inventory_service.exception.InventoryException;
import com.equitasit.inventory_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class InventoryExceptionHandler {

    @ExceptionHandler(value = InventoryException.class)
    public ResponseEntity<StatusDTO> handleProductException(InventoryException ex) {

        log.error("Error while getting the users ", ex);

        if (MsgConstants.INVENTORY_NOT_FOUND == ex.getMsgConstant()) {
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
