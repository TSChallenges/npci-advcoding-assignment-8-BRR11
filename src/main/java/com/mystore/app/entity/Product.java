package com.mystore.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private Integer id;

    @NotBlank(message = "Product name must not be blank")
    private String name;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @Positive(message = "Price must be positive")
    @Min(value = 100, message = "Please don't add any product with price lesser than 100")
    @Max(value = 50000, message = "This platform doesn't allow high priced products. Prices must be <= 50000")
    private Double price;

    @Positive(message = "Stock quantity must be positive")
    @Min(value = 10, message = "Stock quantity must be at least 10")
    @Max(value = 500, message = "Stock quantity must not exceed 500")
    private Integer stockQuantity;

    public Product() {
    }

    public Product(Integer id, String name, String category, Double price, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
