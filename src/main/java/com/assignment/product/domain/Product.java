package com.assignment.product.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    @Id
    private Long id;

    private String title;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
