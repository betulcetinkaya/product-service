package com.assignment.product.service.impl;

import com.assignment.product.domain.Category;
import com.assignment.product.domain.Product;
import com.assignment.product.exception.CategoryNotFoundException;
import com.assignment.product.exception.ProductNotFoundException;
import com.assignment.product.repository.CategoryRepository;
import com.assignment.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException(id);
        }
        return category.get();
    }
}
