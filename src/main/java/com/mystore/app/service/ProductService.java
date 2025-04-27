package com.mystore.app.service;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 🆕 Updated to support Pagination + Sorting
    public List<Product> getAllProducts(int page, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public Product updateProduct(Integer id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return null;

        Product p = optionalProduct.get();
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        productRepository.save(p);
        return p;
    }

    public String deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return "Product Not Found";
        }
        productRepository.delete(optionalProduct.get());
        return "Product Deleted Successfully";
    }

    // ✅ Method to search products by name
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // ✅ Method to filter products by category
    public List<Product> filterProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // ✅ Method to filter products by price range
    public List<Product> filterProductsByPrice(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // ✅ Method to filter products by stock quantity range
    public List<Product> filterProductsByStockQuantity(Integer minStock, Integer maxStock) {
        return productRepository.findByStockQuantityBetween(minStock, maxStock);
    }


}
