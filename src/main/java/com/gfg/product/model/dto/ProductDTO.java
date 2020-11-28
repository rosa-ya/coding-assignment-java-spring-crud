package com.gfg.product.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Rose
 */

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String productId;
    private String title;
    private String description;
    private String brand;
    private BigDecimal price;
    private String color;



}
