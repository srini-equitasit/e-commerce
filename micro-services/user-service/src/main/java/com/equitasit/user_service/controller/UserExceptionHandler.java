package com.equitasit.user_service.controller;

import com.equitasit.user_service.dto.StatusDTO;
import com.equitasit.user_service.exception.UserException;
import com.equitasit.user_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<StatusDTO> handleProductException(UserException ex) {

        log.error("Error while getting the users ", ex);

        if (MsgConstants.USER_NOT_FOUND == ex.getMsgConstant()) {
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
