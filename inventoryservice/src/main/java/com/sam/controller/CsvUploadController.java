package com.sam.controller;

import com.sam.model.Product;
import com.sam.model.Store;
import com.sam.service.ProductService;
import com.sam.service.StoreService;
import com.sam.util.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/csv")
public class CsvUploadController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<Integer> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            List<Store> stores = CsvUtils.parseStoresFromCsv(file);
            List<Product> products = CsvUtils.parseProductsFromCsv(file);
            int recordsProcessed = 0;
            for (Store store : stores) {
                storeService.saveStore(store);
                recordsProcessed++;
            }
            for (Product product : products) {
                productService.saveProduct(product);
                recordsProcessed++;
            }
            return ResponseEntity.ok(recordsProcessed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
        }
    }
}
