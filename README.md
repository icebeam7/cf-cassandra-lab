# Demo Cassandra - Spring Boot Products API

Backend REST con Spring Boot 3 y Apache Cassandra para gestión de Productos.

## Requisitos

- Java 21
- Maven 3.9+
- Docker y Docker Compose

## Estructura del proyecto

```
src/main/java/com/demo/cassandra/
├── DemoCassandraApplication.java
├── controller/
│   └── ProductController.java
├── service/
│   ├── ProductService.java
│   └── ProductServiceImpl.java
├── repository/
│   └── ProductRepository.java
├── model/
│   └── Product.java
├── dto/
│   ├── ProductRequest.java
│   └── ProductResponse.java
└── exception/
    ├── ProductNotFoundException.java
    └── GlobalExceptionHandler.java
```

## Levantar Cassandra con Docker

```bash
docker-compose up -d
```

Espera a que Cassandra esté lista (alrededor de 60 segundos). Ejecuta este comando para revisar si el estado es `up (healthy)`:

```bash
docker container ls 
```

Si dice `health: starting`, espera un poco y vuelve a revisar el estado.

Luego, inicializa el schema:

```bash
docker exec -i cassandra-demo cqlsh < src/main/resources/schema.cql
```

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

## Endpoints

| Método | URL | Descripción |
|--------|-----|-------------|
| POST | `/api/v1/products` | Crear producto |
| GET | `/api/v1/products` | Listar todos los productos |
| GET | `/api/v1/products?category=Electronics` | Filtrar por categoría |
| GET | `/api/v1/products/{id}` | Obtener producto por ID |
| PUT | `/api/v1/products/{id}` | Actualizar producto |
| DELETE | `/api/v1/products/{id}` | Eliminar producto |

## Ejemplo de payload (POST/PUT)

```json
{
  "name": "Laptop Pro",
  "description": "Laptop de alto rendimiento",
  "price": 1299.99,
  "category": "Electronics",
  "stock": 50
}
```
