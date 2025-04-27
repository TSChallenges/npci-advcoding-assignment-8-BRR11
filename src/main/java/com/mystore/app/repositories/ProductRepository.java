package com.mystore.app.repositories;

import com.mystore.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    // TODO
     List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByStockQuantityBetween(Integer minStock, Integer maxStock);

}
