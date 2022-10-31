DROP TABLE IF EXISTS enrollments;

CREATE TABLE enrollments (
    std_id int,
    crs_id VARCHAR(10)
);


DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id VARCHAR(10),
    name VARCHAR(50),
    price float
);