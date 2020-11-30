package com.gfg.product.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

/**
 * @author Rose
 * Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
 * Never write another getter or setter method again, with one annotation your class has a fully featured builder.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO extends RepresentationModel<ProductDTO> {

    private Long id;
    private String productId;
    private String title;
    private String description;
    private String brand;
    private BigDecimal price;
    private String color;


}
