package com.payment.payments.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class RestExceptionHandler {
    Logger logger = Logger.getLogger(RestExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnknownException(Exception e) {
        logger.info( "Exception ocuurec");
        return ResponseEntity.internalServerError().body("Exception Stack Trace" + e.toString());
    }
}
