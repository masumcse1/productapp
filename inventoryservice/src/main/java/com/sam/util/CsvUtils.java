package com.sam.util;

import com.sam.model.Product;
import com.sam.model.Store;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.*;

public class CsvUtils {
    public static List<Store> parseStoresFromCsv(MultipartFile file) throws IOException {
        List<Store> stores = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                Store store = new Store();
                store.setStoreCode(csvRecord.get("Store Code"));
                store.setStoreName(csvRecord.get("Store Name"));
                store.setStoreDescription(csvRecord.get("Store Description"));
                store.setStoreOpeningDate(LocalDate.parse(csvRecord.get("Store Opening Date")));
                // Set other fields
                stores.add(store);
            }
        }
        return stores;
    }

    public static List<Product> parseProductsFromCsv(MultipartFile file) throws IOException {
        List<Product> products = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                Product product = new Product();
                product.setEan(csvRecord.get("Product EAN"));
                product.setName(csvRecord.get("Product Name"));
                product.setDescription(csvRecord.get("Product Description"));
                Map<String, String> extraAttributes = new HashMap<>();
                for (String header : csvParser.getHeaderMap().keySet()) {
                    if (!Arrays.asList("Season", "Store Code", "Store Name", "Store Description", "Store Opening Date", "Product EAN", "Product Name", "Product Description").contains(header)) {
                        extraAttributes.put(header, csvRecord.get(header));
                    }
                }
                product.setExtraAttributes(extraAttributes);
                products.add(product);
            }
        }
        return products;
    }
}
