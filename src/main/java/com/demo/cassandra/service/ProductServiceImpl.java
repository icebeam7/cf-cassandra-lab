package com.demo.cassandra.service;

import com.demo.cassandra.dto.ProductRequest;
import com.demo.cassandra.dto.ProductResponse;
import com.demo.cassandra.exception.ProductNotFoundException;
import com.demo.cassandra.model.Product;
import com.demo.cassandra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        log.debug("Creando producto: {}", request.getName());
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .stock(request.getStock())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Product saved = productRepository.save(product);
        log.debug("Producto creado con ID: {}", saved.getId());
        return toResponse(saved);
    }

    @Override
    public ProductResponse findById(UUID id) {
        log.debug("Buscando producto con ID: {}", id);
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<ProductResponse> findAll() {
        log.debug("Obteniendo todos los productos");
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findByCategory(String category) {
        log.debug("Buscando productos por categoría: {}", category);
        return productRepository.findByCategory(category).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductResponse update(UUID id, ProductRequest request) {
        log.debug("Actualizando producto con ID: {}", id);
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setCategory(request.getCategory());
        existing.setStock(request.getStock());
        existing.setUpdatedAt(Instant.now());

        Product updated = productRepository.save(existing);
        log.debug("Producto actualizado: {}", updated.getId());
        return toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Eliminando producto con ID: {}", id);
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        log.debug("Producto eliminado: {}", id);
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
