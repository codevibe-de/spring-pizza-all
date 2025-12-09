# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an **educational Spring Pizza ordering application** used for Spring Boot training.

It demonstrates layered architecture, repository pattern, and domain-driven design principles before introducing Spring
concepts.

This project makes heavy use of branches to separate the codebase into chapters. Initially the project does
not make use of Sprint at all, yet Spring support is added later and then used extensively.

- **Group ID**: de.codevibe
- **Artifact ID**: pizza-app
- **Java Version**: 17
- **Main Class**: `pizza.PizzaApp`
- **Database**: H2 (embedded TCP server on port 9092)

## Build & Run Commands

### Maven (Primary)

```bash
# Compile
mvn clean compile

# Run application
mvn clean compile exec:java -Dexec.mainClass="pizza.PizzaApp"

# Package
mvn clean package

# Run packaged JAR
java -cp target/pizza-app-005.jar pizza.PizzaApp
```

### Gradle (Secondary)

```bash
# Compile
./gradlew clean compileJava

# Run application
./gradlew run

# Build
./gradlew build
```

### Testing

Currently no tests are implemented (exercise project). Test structure:

```bash
mvn test           # Maven
./gradlew test     # Gradle
```

## Architecture

### Layered Structure

```
Application Layer (PizzaApp)
     ↓
Service Layer (ProductService, CustomerService, OrderService)
     ↓
Repository Layer (ProductRepository interface)
     ↓
Data Access (HashMapProductRepository, JdbcProductRepository)
```

### Domain Organization

- **pizza.product** - Product domain (5 classes)
    - `Product`, `ProductService`, `ProductRepository` (interface)
    - `HashMapProductRepository` (in-memory), `JdbcProductRepository` (H2)
    - `ProductNotFoundException`
- **pizza.customer** - Customer domain (4 classes)
    - `Customer`, `Address`, `CustomerService`
    - `CustomerNotFoundException`
- **pizza.order** - Order domain (2 classes)
    - `Order`, `OrderService`
- **pizza** (core) - Application infrastructure
    - `PizzaApp`, `DataLoader`, `H2TcpServer`, `SchemaScriptRunner`, `PersistenceException`

### Key Design Patterns

- **Repository Pattern**: `ProductRepository` interface with dual implementations (in-memory HashMap and JDBC)
- **Service Layer**: Business logic separated from data access
- **Data Loader**: Template method pattern for populating test data
- **Immutability**: `Product` and `Address` are immutable; `Customer` and `Order` have controlled mutability (ID only)

## Database

### H2 Configuration

- **Mode**: TCP Server (allows external IDE connections)
- **Port**: 9092
- **Database Path**: `~/training.spring.pizza`
- **JDBC URL**: `jdbc:h2:tcp://localhost:9092/~/training.spring.pizza`
- **Schema Script**: `src/main/resources/schema.sql`

### Tables

- `products` (pk, name, price)
- `customers` (id) - **Incomplete schema (TODO)**
- `orders` (id, cst_id, total_price, eta) - FK to customers

### Starting/Stopping Database

Database server lifecycle is managed in `PizzaApp.main()`:

- Start: `H2TcpServer.start()` before services initialization
- Stop: `H2TcpServer.stop()` on application exit

## Entry Point & Service Flow

**Main execution flow** in `PizzaApp.main()`:

1. Start H2 TCP server
2. Create DataSource (JDBC configuration)
3. **Instantiate services (TODO - currently returns null)**
4. Load sample data (`DataLoader.Sample`)
5. Demonstrate three operations:
    - Get product: `productService.getProduct("P-10")`
    - Get customer: `customerService.getCustomerByPhoneNumber("+49 123 456789")`
    - Place order: `orderService.placeOrder(phoneNumber, productQuantities)`
6. Stop database server

### Core Service Methods

**ProductService**:

- `getAllProducts()` - retrieve all products
- `getProduct(String productId)` - find by ID
- `getTotalPrice(Map<String, Integer>)` - **TODO: calculate order total**
- `createProduct(Product)` - add new product

**CustomerService** (in-memory ArrayList):

- `getCustomerByPhoneNumber(String)` - find by phone
- `getAllCustomers()` - retrieve all
- `createCustomer(Customer)` - add new customer

**OrderService** (orchestrates Product + Customer services):

- `placeOrder(String phoneNumber, Map<String, Integer>)` - main workflow
- `getTodaysDiscountRate()` - conditional discounts by day of week
- `getOrders()` - retrieve all orders

## Initial Data

Initial data can be loaded using the `DataLoader` class, which has subtypes for "None" or "Sample" data variations.

**Products** (loaded automatically):

- S-01: Thunfisch Salat (€6.90)
- S-02: Salat Italiano (€7.90)
- S-03: Romana Salat (€8.90)
- P-10: Pizza Margarita (€5.50)
- P-11: Pizza Capricciosa (€7.50)
- P-12: Pizza Spinat und Feta (€7.00)

**Customers**:

- Enrico Pallazzo, +49 123 456789, Wasserstr. 123, Atlantis
- Elizabeth Magie, +1 77 551237, Schlossallee 1, Monopolhausen

## Key Files Reference

| File                                   | Purpose                     |
|----------------------------------------|-----------------------------|
| `pizza/PizzaApp.java:main()`           | Application entry point     |
| `pizza/product/ProductService.java`    | Product business logic      |
| `pizza/customer/CustomerService.java`  | Customer business logic     |
| `pizza/order/OrderService.java`        | Order orchestration         |
| `pizza/product/ProductRepository.java` | Data access interface       |
| `pizza/DataLoader.java`                | Test data population        |
| `src/main/resources/schema.sql`        | Database schema             |
| `EXERCISES.md`                         | Training exercises (German) |
