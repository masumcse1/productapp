package com.sam.service;

import com.sam.exception.ResourceNotFoundException;
import com.sam.model.Product;
import com.sam.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(String ean) {
        return productRepository.findById(ean)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Page<Product> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> filterProducts(Map<String, List<String>> filterAttributes) {
        // Implement filtering logic based on attributes
        return new ArrayList<>();
    }
}