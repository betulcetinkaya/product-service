package com.assignment.product.service.impl;

import com.assignment.product.domain.Product;
import com.assignment.product.exception.ProductNotFoundException;
import com.assignment.product.repository.ProductRepository;
import com.assignment.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(id);
        }
        return product.get();
    }
}
