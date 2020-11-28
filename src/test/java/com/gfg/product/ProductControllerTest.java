package com.gfg.product;

import com.gfg.product.model.dto.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rose
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Tag("integration-test")
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("Find all products work successfully")
    public void findAllSuccessfully() {
        ResponseEntity<Object[]> responseEntity = this.restTemplate.getForEntity
                ("http://localhost:" + port + "/products/findAll", Object[].class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Find product with id work successfully")
    public void findByIdSuccessfully() {
        ResponseEntity<ProductDTO> responseEntity = this.restTemplate.getForEntity
                ("http://localhost:" + port + "/products/findById/1", ProductDTO.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Find product with id throws IllegalArgumentException")
    public void findByIdThrowIllegalArgumentException() {
        ResponseEntity<ProductDTO> responseEntity = this.restTemplate.getForEntity
                ("http://localhost:" + port + "/products/findById/11", ProductDTO.class);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Save product work successfully")
    public void saveSuccessfully() {
        ProductDTO productDTO= ProductDTO.builder().color("Green").price(BigDecimal.valueOf(500.00)).brand("LC").title("Jeans").productId("GAS1234567").description("Slim fit jeans").build();
        ResponseEntity<ProductDTO> responseEntity = this.restTemplate.postForEntity
                ("http://localhost:" + port + "/products/new",productDTO, ProductDTO.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Update product work successfully")
    public void updateSuccessfully() {
        ProductDTO productDTO= ProductDTO.builder().color("Green").price(BigDecimal.valueOf(500.00)).brand("LC").title("Jeans").productId("GAS1234567").description("Slim fit jeans").build();
        ResponseEntity<ProductDTO> responseEntity = this.restTemplate.postForEntity
                ("http://localhost:" + port + "/products/updateById/1",productDTO, ProductDTO.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Update product throws ProductNotFoundException")
    public void updateThrowProductNotFoundException() {
        ProductDTO productDTO= ProductDTO.builder().color("Green").price(BigDecimal.valueOf(500.00)).brand("LC").title("Jeans").productId("GAS1234567").description("Slim fit jeans").build();
        ResponseEntity<ProductDTO> responseEntity = this.restTemplate.postForEntity
                ("http://localhost:" + port + "/products/updateById/11",productDTO, ProductDTO.class);
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("delete product work successfully")
    public void deleteSuccessfully() {
        this.restTemplate.delete("http://localhost:" + port + "/products/deleteById/1");
        ResponseEntity<ProductDTO> responseEntity = this.restTemplate.getForEntity
                ("http://localhost:" + port + "/products/findById/1", ProductDTO.class);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }



}
