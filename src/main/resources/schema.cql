-- Crear keyspace
CREATE KEYSPACE IF NOT EXISTS products_db
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE products_db;

-- Crear tabla de productos
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    name TEXT,
    description TEXT,
    price DECIMAL,
    category TEXT,
    stock INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Índice secundario para búsqueda por categoría
CREATE INDEX IF NOT EXISTS products_category_idx ON products (category);
