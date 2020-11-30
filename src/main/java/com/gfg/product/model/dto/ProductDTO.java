package com.gfg.product.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Rose
 *  Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
 *     Never write another getter or setter method again, with one annotation your class has a fully featured builder.
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
