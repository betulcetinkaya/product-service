package com.assignment.product.service;

import com.assignment.product.domain.Category;

public interface CategoryService {
    Category create(Category category);

    Category getById(String id);
}
