package com.sam.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Product {
    @Id
    private String ean;
    private String name;
    private String description;
    @ElementCollection
    private Map<String, String> extraAttributes = new HashMap<>();
    // Other fields
}