package com.gfg.product.model.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Rose
 */
@Builder
@Getter
@Setter
public class ErrorMessage extends RuntimeException {

    private final  int statusCode;
    private final  Date timestamp;
    private final  String message;
    private final  String description;


}
