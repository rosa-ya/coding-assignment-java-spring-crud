package com.gfg.product;


import com.gfg.product.model.Product;
import com.gfg.product.model.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {



    void updateProductFromDto(ProductDTO updateProductDto, @MappingTarget Product product);



}


