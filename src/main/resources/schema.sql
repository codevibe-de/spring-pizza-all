DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;

CREATE TABLE products
(
    pk    VARCHAR(10) NOT NULL,
    name  VARCHAR(50) NOT NULL,
    price FLOAT       NOT NULL,
    PRIMARY KEY (`pk`)
);

CREATE TABLE orders
(
    id          LONG  NOT NULL,
    cst_id      LONG  NOT NULL,
    total_price FLOAT NOT NULL,
    eta         TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE customers
(
    id LONG NOT NULL,
    -- TODO other fields
    PRIMARY KEY (`id`)
);

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_customer
        foreign key (cst_id)
            references customers;