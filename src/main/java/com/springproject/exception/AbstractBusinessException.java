package com.springproject.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public abstract class AbstractBusinessException extends RuntimeException {
    public static final String TYPE = "BUSINESS";
    private final String message;

    protected AbstractBusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    protected AbstractBusinessException(String message) {
        super(message);
        this.message = message;
    }
}
