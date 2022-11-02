package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/daily/data")
public class DailyDataPersistController {

    private final ProductService productService;

    public DailyDataPersistController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<?> getDailyInformation() throws Exception {
        String json = readFileAsString("/Users/drenk/csvjson.json");
//
        productService.persistDailyProductInfo(json);

        return ResponseEntity.ok("Information got");
    }
    public String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}