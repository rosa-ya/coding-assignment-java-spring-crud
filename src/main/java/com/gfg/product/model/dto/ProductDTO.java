package com.gfg.product.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

/**
 * @author Rose
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
