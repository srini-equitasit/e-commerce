CREATE TABLE inventory(
    qty integer,
    seller_id integer,
    product_id integer,
    primary key (seller_id,product_id)
);
-- data
INSERT INTO inventory (seller_id, product_id,qty) VALUES (60001, 50001,4000);
-- log
CREATE TABLE inventory_log(
    id integer NOT NULL AUTO_INCREMENT,
    qty integer,
    tx_qty integer,
    type varchar(255),
    user_id integer,
    seller_id integer,
    product_id integer,
    primary key (id)
);
