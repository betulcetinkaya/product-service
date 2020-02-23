package com.assignment.product.service;

import com.assignment.product.ServiceBaseTest;
import com.assignment.product.domain.Product;
import com.assignment.product.exception.ProductNotFoundException;
import com.assignment.product.repository.ProductRepository;
import com.assignment.product.service.impl.ProductServiceImpl;
import com.assignment.product.testdata.ProductTestData;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceTest extends ServiceBaseTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void testCreate_SendValidProduct_ReturnProduct() {
        Product product = ProductTestData.getProduct();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product created = productService.create(product);

        verify(productRepository).save(any(Product.class));
        Assert.assertEquals(product.getId(), created.getId());
        Assert.assertEquals(product.getTitle(), created.getTitle());
        Assert.assertEquals(product.getPrice(), created.getPrice());
        Assert.assertEquals(product.getCategory().getId(), created.getCategory().getId());
    }

    @Test
    public void testGetById_RecordNotFound_ThrowException() {
        String id = "PRODUCT-001";
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        thrown.expect(ProductNotFoundException.class);
        thrown.expectMessage(id);

        productService.getById(id);

        verify(productRepository).findById(anyString());
    }

    @Test
    public void testGetBy_SendExistingId_ReturnProduct() {
        Product product = ProductTestData.getProduct();
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        Product found = productService.getById(product.getId());

        verify(productRepository).findById(anyString());
        Assert.assertEquals(product.getId(), found.getId());
        Assert.assertEquals(product.getTitle(), found.getTitle());
        Assert.assertEquals(product.getPrice(), found.getPrice());
        Assert.assertEquals(product.getCategory().getId(), found.getCategory().getId());
    }


}
