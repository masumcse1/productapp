package com.sam.controller;

import com.sam.service.ProductService;
import com.sam.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv22")
public class CsvUploadController_OLD {
    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> uploadCsv(@RequestParam("file") MultipartFile file) {
        // Process CSV file and save data
        int recordsProcessed = 0;
        // CSV parsing logic and saving entities
        return ResponseEntity.ok(recordsProcessed);
    }
}