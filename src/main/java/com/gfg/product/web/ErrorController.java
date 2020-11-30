package com.gfg.product.web;


import com.gfg.product.model.errors.ErrorCreation;
import com.gfg.product.model.errors.ErrorMessageResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author Rose
 * This class use @RestControllerAdvice for exception handling in Restful API
 */

@RestControllerAdvice
public class ErrorController {


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorMessageResponse> emptyResultDataAccessExceptionHandler(final EmptyResultDataAccessException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ErrorMessageResponse.builder()
                        .timestamp(new Date())
                        .statusCode(INTERNAL_SERVER_ERROR.value())
                        .message(ErrorCreation.INVALID_ID.getMessage())
                        .description(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessageResponse> methodArgumentNotValidExceptionHandler(final MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(ErrorMessageResponse.builder()
                        .timestamp(new Date())
                        .statusCode(BAD_REQUEST.value())
                        .message(ErrorCreation.INVALID_INPUT.getMessage())
                        .description(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessageResponse> noSuchElementExceptionHandler(final NoSuchElementException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(ErrorMessageResponse.builder()
                        .timestamp(new Date())
                        .statusCode(BAD_REQUEST.value())
                        .message(ErrorCreation.INVALID_ID.getMessage())
                        .description(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessageResponse> illegalArgumentExceptionHandler(final IllegalArgumentException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(ErrorMessageResponse.builder()
                        .timestamp(new Date())
                        .statusCode(BAD_REQUEST.value())
                        .message(ErrorCreation.INVALID_ID.getMessage())
                        .description(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> entityNotFoundExceptionHandler(final EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessageResponse.builder()
                        .timestamp(new Date())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(ErrorCreation.INVALID_ID.getMessage())
                        .description(ex.getMessage())
                        .build()
                );
    }


}
