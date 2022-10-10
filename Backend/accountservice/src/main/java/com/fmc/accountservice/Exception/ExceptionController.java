package com.fmc.accountservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class controls the custom exceptions for an entity
 *
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> customException(CustomException exception) {
        return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.NOT_FOUND);
    }

}
