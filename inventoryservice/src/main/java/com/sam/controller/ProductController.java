package com.sam.controller;

import com.sam.model.Product;
import com.sam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{ean}")
    public ResponseEntity<Product> getProduct(@PathVariable String ean) {
        Product product = productService.getProduct(ean);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(@RequestBody Map<String, List<String>> filterAttributes) {
        List<Product> products = productService.filterProducts(filterAttributes);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> listProducts(@RequestParam int page, @RequestParam int page_size) {
        Pageable pageable = PageRequest.of(page, page_size, Sort.by("ean").ascending());
        Page<Product> products = productService.listProducts(pageable);
        return ResponseEntity.ok(products);
    }
}