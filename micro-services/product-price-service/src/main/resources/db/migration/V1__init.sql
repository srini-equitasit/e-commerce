CREATE TABLE product_price(
    id integer NOT NULL AUTO_INCREMENT,
    price DOUBLE,
    product_id integer,
    primary key (id)
);
-- data
INSERT INTO product_price (id, price,product_id) VALUES (20001, 800,10001);
INSERT INTO product_price (id, price,product_id) VALUES (20002, 400,10002);
INSERT INTO product_price (id, price,product_id) VALUES (20003, 500,10003);
INSERT INTO product_price (id, price,product_id) VALUES (20004, 450,10004);

