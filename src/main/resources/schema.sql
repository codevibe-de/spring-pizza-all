DROP SEQUENCE IF EXISTS CUSTOMERS_SEQ;
DROP SEQUENCE IF EXISTS ORDERS_SEQ;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;


CREATE SEQUENCE CUSTOMERS_SEQ INCREMENT BY 50;
CREATE SEQUENCE ORDERS_SEQ INCREMENT BY 50;

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