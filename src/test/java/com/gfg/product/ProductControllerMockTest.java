package com.gfg.product;

import com.gfg.product.model.Product;
import com.gfg.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * @author Rose
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductControllerMockTest {


    @Autowired
    private ProductRepository productRepository;


    @Test
    @DisplayName("Find all products work successfully")
    public void findAllSuccessfully() {
        Iterable<Product> products = productRepository.findAll();
        int nOfProducts = 5;
        assertThat(products).hasSize(nOfProducts);
        assertNotNull(products);
    }


}
