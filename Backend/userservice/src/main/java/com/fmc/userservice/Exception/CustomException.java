package com.fmc.userservice.Exception;

import org.springframework.http.HttpStatus;

/**
 * This class creates the custom exception.
 *
 * @params:
 *      - ErrorMessage: A simplified reason for the cause of the error
 *      - ErrorCode: The exception message
 *      - ErrorStatus: The HTTP response status
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorMessage;
    private String errorCode;
    private HttpStatus errorStatus;

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException() {

    }

    public CustomException(String errorMessage, HttpStatus errorStatus) {
        this.errorMessage = errorMessage;
        this.errorStatus = errorStatus;
    }

    public CustomException(String errorMessage, String errorCode, HttpStatus errorStatus) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
    }

}