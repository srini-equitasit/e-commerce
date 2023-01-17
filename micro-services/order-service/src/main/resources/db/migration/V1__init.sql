CREATE TABLE orders(
    id integer NOT NULL AUTO_INCREMENT,
    email varchar(255),
    status varchar(255),
    user_id integer,
    order_created_dt DATETIME,
    primary key (id)
);

CREATE TABLE product_order(
    id integer NOT NULL AUTO_INCREMENT,
    product_id integer,
    qty integer,
    price DOUBLE,
    seller_id integer,
    order_id integer,
    primary key (id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);
