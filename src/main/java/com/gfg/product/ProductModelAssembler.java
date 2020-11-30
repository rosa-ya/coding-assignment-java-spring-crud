package com.gfg.product;


import com.gfg.product.model.Product;
import com.gfg.product.model.dto.ProductDTO;
import com.gfg.product.web.ProductController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler
        extends RepresentationModelAssemblerSupport<Product, ProductDTO> {

    public ProductModelAssembler() {
        super(ProductController.class, ProductDTO.class);
    }

    @Override
    public ProductDTO toModel(Product product) {
        ProductDTO productDTO = instantiateModel(product).builder()
                .id(product.getId())
                .productId(product.getProductId())
                .title(product.getTitle())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .color(product.getColor())
                .build();

        productDTO.add(linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel().withType("GET"));
        productDTO.add(linkTo(methodOn(ProductController.class).newProduct(productDTO)).withRel("create").withType("POST"));
        productDTO.add(linkTo(methodOn(ProductController.class).deleteProduct(productDTO.getId())).withRel("delete").withType("DELETE"));
        productDTO.add(linkTo(methodOn(ProductController.class).updateProduct(productDTO, productDTO.getId())).withRel("update").withType("POST"));

        return productDTO;
    }


    public Product buildProduct(ProductDTO product) {
        return Product.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .color(product.getColor())
                .build();
    }

}
