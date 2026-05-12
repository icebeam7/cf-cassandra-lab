package com.demo.cassandra.service;

import com.demo.cassandra.dto.ProductRequest;
import com.demo.cassandra.dto.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse findById(UUID id);

    List<ProductResponse> findAll();

    List<ProductResponse> findByCategory(String category);

    ProductResponse update(UUID id, ProductRequest request);

    void delete(UUID id);
}
