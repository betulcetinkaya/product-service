package com.assignment.product.repository;

import com.assignment.product.ProductConstants;
import com.assignment.product.RepositoryBaseTest;
import com.assignment.product.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RepositoryBaseRepositoryTest extends RepositoryBaseTest {

    @Autowired
    ProductRepository productRepository;

    @Before
    public void onSetup() throws Exception {
        this.objectMapper = new ObjectMapper();
        if (!mongoTemplate.collectionExists(ProductConstants.PRODUCT_DOCUMENT_NAME))
        mongoTestDataLoader.loadTestDataIntoDb(ProductConstants.PRODUCT_DOCUMENT_NAME);

        if (!mongoTemplate.collectionExists(ProductConstants.CATEGORY_DOCUMENT_NAME))
            mongoTestDataLoader.loadTestDataIntoDb(ProductConstants.CATEGORY_DOCUMENT_NAME);
        System.out.println("");
    }

    @Test
    public void shouldFindById() {
        Optional<Product> product = productRepository.findById("1");
        Assert.assertTrue(product.isPresent());

        Assert.assertNotNull(product.get().getCategory());
    }

    @After
    public void clearDb() {
        try {
            mongoTemplate.dropCollection(ProductConstants.PRODUCT_DOCUMENT_NAME);
            mongoTemplate.dropCollection(ProductConstants.CATEGORY_DOCUMENT_NAME);
        } catch (Exception e) {
            fail("error on After", e);
        }
    }

}
