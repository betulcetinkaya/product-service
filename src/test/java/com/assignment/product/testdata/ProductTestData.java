package com.assignment.product.testdata;

import com.assignment.product.domain.Category;
import com.assignment.product.domain.Product;

import java.math.BigDecimal;

public class ProductTestData {

    public static Product getProduct() {
        Product product = new Product();
        product.setId("PRODUCT-001");
        product.setTitle("PRODUCT-TITLE-001");
        product.setPrice(new BigDecimal(100.5));
        product.setCategory(new Category());
        product.getCategory().setId("CATEGORY-001");
        return product;
    }
}
