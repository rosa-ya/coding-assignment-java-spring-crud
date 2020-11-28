package com.gfg.product.model.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Rose
 */
@Getter
@Setter
@Builder
public class ErrorMessageResponse {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
