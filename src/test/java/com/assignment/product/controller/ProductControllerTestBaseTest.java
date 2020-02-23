package com.assignment.product.controller;

import com.assignment.product.ControllerBaseTest;
import com.assignment.product.domain.Product;
import com.assignment.product.service.ProductService;
import com.assignment.product.testdata.ProductTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles({"test"})
@WebMvcTest(ProductController.class)
public class ProductControllerTestBaseTest extends ControllerBaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeClass
    public static void init() {
        baseAddress = "/products";
    }

    @Test
    public void testCreateProduct_SendValidRequest_ReturnCreated() throws Exception {
        Product product = ProductTestData.getProduct();
        when(productService.create(any(Product.class))).thenReturn(product);

        ResultActions perform = mockMvc.perform(post(baseAddress)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)));


        perform.andExpect(status().isCreated());
    }

    @Test
    public void testGetProduct_SendId_GetAProduct() throws Exception {
        Product product = ProductTestData.getProduct();
        when(productService.getById(product.getId())).thenReturn(product);

        ResultActions resultActions = mockMvc.perform(get(baseAddress + "/" + product.getId())
                .contentType(mediaType));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(product.getId())));

        verify(productService).getById(anyString());
    }



}
