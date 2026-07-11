create database freshmart;

use freshmart;

#CUSTOMER
CREATE TABLE customer(
    cid INT PRIMARY KEY AUTO_INCREMENT,
    cname VARCHAR(100) NOT NULL,
    phone BIGINT NOT NULL UNIQUE,
    mail VARCHAR(100) NOT NULL UNIQUE,
    pwd VARCHAR(255) NOT NULL
);

#CATEGORY
CREATE TABLE category(
    cat_id INT PRIMARY KEY AUTO_INCREMENT,
    cat_name VARCHAR(50) NOT NULL UNIQUE
);

#PRODUCT
CREATE TABLE product(
    pid INT PRIMARY KEY AUTO_INCREMENT,
    pname VARCHAR(100) NOT NULL,
    price_per_kg DECIMAL(8,2) NOT NULL,
    cat_id INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,

    FOREIGN KEY(cat_id)
    REFERENCES category(cat_id)
);

#CART
CREATE TABLE cart(
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    cid INT NOT NULL UNIQUE,
    total_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,

    FOREIGN KEY(cid)
    REFERENCES customer(cid)
);

#CART_ITEM
CREATE TABLE cart_item(
    ci_id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT NOT NULL,
    pid INT NOT NULL,
    quantity DECIMAL(5,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,

    FOREIGN KEY(cart_id)
    REFERENCES cart(cart_id),

    FOREIGN KEY(pid)
    REFERENCES product(pid)
);

#ORDERS
CREATE TABLE orders(
    oid INT PRIMARY KEY AUTO_INCREMENT,
    cid INT NOT NULL,
    o_date DATE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,

    FOREIGN KEY(cid)
    REFERENCES customer(cid)
);

#ORDER_ITEM
CREATE TABLE order_item(
    oi_id INT PRIMARY KEY AUTO_INCREMENT,
    oid INT NOT NULL,
    pid INT NOT NULL,
    quantity DECIMAL(5,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,

    FOREIGN KEY(oid)
    REFERENCES orders(oid),

    FOREIGN KEY(pid)
    REFERENCES product(pid)
);

#PAYMENT
CREATE TABLE payment(
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    payment_mode VARCHAR(50) NOT NULL,
    status VARCHAR(30) NOT NULL,
    oid INT NOT NULL UNIQUE,
    gst DECIMAL(10,2) NOT NULL,
    final_price DECIMAL(10,2) NOT NULL,

    FOREIGN KEY(oid)
    REFERENCES orders(oid)
);