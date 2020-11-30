package com.gfg.product.web;

import com.gfg.product.ProductMapper;
import com.gfg.product.model.Product;
import com.gfg.product.model.dto.ProductDTO;
import com.gfg.product.model.errors.ProductNotFoundException;
import com.gfg.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /*
        Return the all products
        @return  ResponseEntity<List<ProductDTO>>
     */
    @GetMapping("findAll")
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll().stream().map(this::buildProductDTO).collect(Collectors.toList()));
    }

    public ProductDTO buildProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .productId(product.getProductId())
                .title(product.getTitle())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .color(product.getColor())
                .build();
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

    /*
            Return the product with requested id
            @param id
            @return ResponseEntity<ProductDTO>

       */
    @GetMapping(value = "findById/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findById(id).map(this::buildProductDTO).orElseThrow(() -> new IllegalArgumentException()));
    }

    /*
         save new product with new id
         @param ProductDTO
         @return ResponseEntity<ProductDTO>
     */
    @PostMapping("/new")
    public ResponseEntity<ProductDTO> newProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.OK).body(buildProductDTO(productRepository.save(buildProduct(product))));
    }

    /*
          update existed id
          for find which fields is going to change , use mapper feature from "mapstruct" library
          @param ProductDTO and id
          @return ResponseEntity<ProductDTO>
     */
    @PostMapping(value = "updateById/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO product, @PathVariable Long id) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        productMapper.updateProductFromDto(product, productToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(buildProductDTO(productRepository.save(productToUpdate)));
    }

    /*
        delete requested id , if the id is exist
        @param id
        @return ResponseEntity<String>
    */
    @DeleteMapping(value = "deleteById/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("The product deleted successfully");
    }
}