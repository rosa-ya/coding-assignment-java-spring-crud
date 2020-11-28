package com.gfg.product.model.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author Rose
 */
@Getter
@AllArgsConstructor
public enum ErrorCreation {

    INVALID_ID(INTERNAL_SERVER_ERROR, "Product with this Id not found"),
    INVALID_INPUT(BAD_REQUEST, "Input is not valid");

    private HttpStatus status;
    private String message;

    public ErrorMessage asErrorResult(final Object... params) {
        return ErrorMessage.builder()
                .timestamp(new Date())
                .statusCode(status.value())
                .message(MessageFormat.format(message, params))
                .build();

    }

}
