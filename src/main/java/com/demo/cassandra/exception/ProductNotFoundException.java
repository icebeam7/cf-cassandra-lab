package com.demo.cassandra.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(java.util.UUID id) {
        super("Producto no encontrado con ID: " + id);
    }
}
