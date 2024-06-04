-- Crear el esquema
CREATE SCHEMA IF NOT EXISTS ecommerce;

-- Crear la tabla brand
CREATE TABLE IF NOT EXISTS ecommerce.brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Crear la tabla price_list
CREATE TABLE IF NOT EXISTS ecommerce.price_list (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

-- Crear la tabla product
CREATE TABLE IF NOT EXISTS ecommerce.product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL
);

-- Crear la tabla price con una restricción de unicidad
CREATE TABLE IF NOT EXISTS ecommerce.price (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    price_list_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    priority INT NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    curr VARCHAR(255) NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES ecommerce.brand(id),
    FOREIGN KEY (price_list_id) REFERENCES ecommerce.price_list(id),
    FOREIGN KEY (product_id) REFERENCES ecommerce.product(id),
    UNIQUE (brand_id, price_list_id, product_id, start_date, end_date)
);

-- Insertar datos en la tabla brand
INSERT INTO ecommerce.brand (id, name) VALUES (1, 'ZARA');

-- Insertar datos en la tabla price_list
INSERT INTO ecommerce.price_list (id, description) VALUES (1, 'Price List 1');
INSERT INTO ecommerce.price_list (id, description) VALUES (2, 'Price List 2');
INSERT INTO ecommerce.price_list (id, description) VALUES (3, 'Price List 3');
INSERT INTO ecommerce.price_list (id, description) VALUES (4, 'Price List 4');

-- Insertar datos en la tabla product
INSERT INTO ecommerce.product (id, name, code) VALUES (35455, 'CAMISA 100 % LINO', '3090/470');

-- Insertar datos en la tabla price
INSERT INTO ecommerce.price (brand_id, price_list_id, product_id, start_date, end_date, priority, price, curr) VALUES
(1, 1, 35455, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 'EUR'),
(1, 2, 35455, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 'EUR'),
(1, 3, 35455, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 'EUR'),
(1, 4, 35455, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 'EUR');