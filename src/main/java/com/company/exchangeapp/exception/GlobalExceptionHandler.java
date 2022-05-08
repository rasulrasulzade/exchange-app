package com.company.exchangeapp.exception;

import com.company.exchangeapp.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleException(Exception exc) {
        String msg = exc.getMessage();
        ErrorModel errorResponse = new ErrorModel(msg != null ? msg : "Error occurred!");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorModel> handleException(CustomException exc) {
        return new ResponseEntity<>(new ErrorModel(exc.getMessage()), exc.getStatus());
    }
}
