package com.ecommerce.controller;

import com.ecommerce.dto.products.ProductsByCategoryRequest;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/api/v1/products")
public class ProductController {

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private final ProductService productService;

    @PostMapping("/getByCategory")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody ProductsByCategoryRequest productsByCategoryRequest){
        return ResponseEntity.ok(productService.getProductsByCategoryStandard(productsByCategoryRequest.getCategory()));
    }
}
