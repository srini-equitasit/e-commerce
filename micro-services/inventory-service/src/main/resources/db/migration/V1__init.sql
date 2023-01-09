CREATE TABLE inventory(
    qty integer,
    seller_id integer,
    product_id integer,
    primary key (seller_id,product_id)
);
-- data
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30001, 10001,10);
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30002, 10002,20);
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30003, 10003,30);
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30004, 10004,40);
--
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30005, 10001,10);
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30006, 10002,20);
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30007, 10003,30);
INSERT INTO inventory (seller_id, product_id,qty) VALUES (30008, 10004,40);

-- log
CREATE TABLE inventory_log(
    id integer NOT NULL AUTO_INCREMENT,
    qty integer,
    tx_qty integer,
    type varchar(255),
    user_id integer,
    seller_id integer,
    product_id integer,
    order_id integer,
    primary key (id)
);
