package com.springproject.exception;

@SuppressWarnings("serial")
public class InvalidGenericException extends AbstractBusinessException {
    public InvalidGenericException(String message) {
        super(message);
    }
    
    public InvalidGenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
