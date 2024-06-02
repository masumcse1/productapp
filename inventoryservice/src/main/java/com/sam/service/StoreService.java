package com.sam.service;

import com.sam.exception.ResourceNotFoundException;
import com.sam.model.Store;
import com.sam.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    public Store getStore(String storeCode) {
        return storeRepository.findById(storeCode).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    public Page<Store> listStores(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }
}