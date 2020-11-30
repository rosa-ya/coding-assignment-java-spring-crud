package com.gfg.product.web;

import com.gfg.product.ProductMapper;
import com.gfg.product.ProductModelAssembler;
import com.gfg.product.model.Product;
import com.gfg.product.model.dto.ProductDTO;
import com.gfg.product.model.errors.ProductNotFoundException;
import com.gfg.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/products")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class ProductController {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductModelAssembler productModelAssembler;
    private final PagedResourcesAssembler<Product> pagedResourcesAssembler;

    /*
        Return the all products , containing sorting, pagination and links
        @param page size and sort field
        @return ResponseEntity<PagedModel<ProductDTO>>
     */
    @GetMapping("findAll")
    public ResponseEntity<PagedModel<ProductDTO>> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        PagedModel<ProductDTO> collModel = pagedResourcesAssembler.toModel(products, productModelAssembler);
        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }

    /*
          Return the product with requested id
          @param id
          @return ResponseEntity<ProductDTO>

     */
    @GetMapping(value = "findById/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(productModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /*
         save new product with new id
         @param ProductDTO
         @return ResponseEntity<ProductDTO>
     */
    @PostMapping("/new")
    public ResponseEntity<ProductDTO> newProduct(@RequestBody ProductDTO product) {
        return new ResponseEntity<>(productModelAssembler.toModel(productRepository.save(productModelAssembler.buildProduct(product))), HttpStatus.OK);

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
        return new ResponseEntity<>(productModelAssembler.toModel(productRepository.save(productToUpdate)), HttpStatus.OK);
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

    /*
        Search a product using any of its fields, can also be more than 1 field.
        result is containing sorting, pagination and links
        @param  page size and search fields and sort field
        @return ResponseEntity<PagedModel<ProductDTO>>
    */
    @GetMapping(value = "/search")
    public ResponseEntity<PagedModel<ProductDTO>> search(
            @And({
                    @Spec(path = "productId", params = "productId", spec = Equal.class),
                    @Spec(path = "title", params = "title", spec = Like.class),
                    @Spec(path = "description", params = "description", spec = Like.class),
                    @Spec(path = "brand", params = "brand", spec = Like.class),
                    @Spec(path = "price", params = "price", spec = Equal.class),
                    @Spec(path = "color", params = "color", spec = Like.class)
            }) Specification<Product> spec, Pageable pageable
    ) {

        Page<Product> products = productRepository.findAll(spec, pageable);
        PagedModel<ProductDTO> collModel = pagedResourcesAssembler.toModel(products, productModelAssembler);
        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }


}