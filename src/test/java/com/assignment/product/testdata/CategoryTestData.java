package com.assignment.product.testdata;

import com.assignment.product.domain.Category;
import com.assignment.product.domain.Product;

import java.math.BigDecimal;

public class CategoryTestData {

    public static Category getCategory() {
        Category category = new Category();
        category.setId("CATEGORY-001");
        category.setParentId(null);
        category.setTitle("CATEGORY-TITLE-001");
        return category;
    }
}
