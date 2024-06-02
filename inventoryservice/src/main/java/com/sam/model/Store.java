package com.sam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Store {
    @Id
    private String storeCode;
    private String season;
    private String storeName;
    private String storeDescription;
    private LocalDate storeOpeningDate;
    // Other fields
}

