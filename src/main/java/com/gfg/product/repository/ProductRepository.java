package com.gfg.product.repository;

import com.gfg.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>,
        PagingAndSortingRepository<Product, Long> {

}
