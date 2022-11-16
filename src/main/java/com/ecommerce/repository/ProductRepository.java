package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(String sku);

    List<Product> findAllByTitleContains(String keyword);

    List<Product> findAllByContentContains(String keyword);

    List<Product> findAllByProductBrand(String productBrand);

}

