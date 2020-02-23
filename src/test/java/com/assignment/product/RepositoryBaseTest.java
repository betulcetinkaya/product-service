package com.assignment.product;

import com.assignment.product.dataloader.MongoTestDataLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class RepositoryBaseTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MongoTestDataLoader mongoTestDataLoader;

    protected ObjectMapper objectMapper;

}
