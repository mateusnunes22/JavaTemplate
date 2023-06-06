package com.springproject.exception;

import com.springproject.entrypoint.controller.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> generateException(ResponseStatusException exception) {
        ErrorDto dto = new ErrorDto();
        dto.setTimestamp(new Date().toString());
        dto.setCodeStatus(String.valueOf(exception.getStatus().value()));
        dto.setErrorMessage(exception.getMessage());
        log.error("Response exception: ", exception, dto);
        return new ResponseEntity<>(dto, exception.getStatus());
    }

    public static <T> T hibernateExceptionAndThrow(Exception exception, String errorMessage, Object objectRef) throws HibernateException {
        log.error(errorMessage, exception, objectRef);
        throw new HibernateException(errorMessage);
    }

    public static <T> T usernameExceptionAndThrow(Exception exception, String errorMessage, Object objectRef) throws HibernateException {
        log.error(errorMessage, exception, objectRef);
        throw new UsernameNotFoundException(errorMessage);
    }
    public static <T> T entityExceptionAndThrow(Exception exception, String errorMessage, Object objectRef) throws HibernateException {
        log.error(errorMessage, exception, objectRef);
        throw new EntityNotFoundException(errorMessage);
    }

    public static <T> T securityExceptionAndThrow(Exception exception, String errorMessage, Object objectRef) throws HibernateException {
        log.error(errorMessage, exception, objectRef);
        throw new SecurityException(errorMessage);
    }

}
