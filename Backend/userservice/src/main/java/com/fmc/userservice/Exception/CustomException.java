package com.fmc.userservice.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * This class creates the custom exception.
 *
 * @params:
 *      - ErrorMessage: A simplified reason for the cause of the error
 *      - ErrorCode: The exception message
 *      - ErrorStatus: The HTTP response status
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorMessage;
    private String errorCode;
    private HttpStatus errorStatus;
    private Exception errorException;

    public CustomException(String errorMessage, HttpStatus errorStatus) {
        this.errorMessage = errorMessage;
        this.errorStatus = errorStatus;
    }

    public CustomException(String errorMessage, String errorCode, HttpStatus errorStatus) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
    }

    public CustomException(String errorMessage, HttpStatus errorStatus, Exception errorException) {
        this.errorMessage = errorMessage;
        this.errorStatus = errorStatus;
        this.errorException = errorException;
    }
}