package com.sam.controller;

import com.sam.model.Store;
import com.sam.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/{code}")
    public ResponseEntity<Store> getStore(@PathVariable String code) {
        Store store = storeService.getStore(code);
        return ResponseEntity.ok(store);
    }

    @GetMapping
    public ResponseEntity<Page<Store>> listStores(@RequestParam int page, @RequestParam int page_size) {
        Pageable pageable = PageRequest.of(page, page_size, Sort.by("storeCode").ascending());
        Page<Store> stores = storeService.listStores(pageable);
        return ResponseEntity.ok(stores);
    }
}