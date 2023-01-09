CREATE TABLE delivery_info(
    id integer NOT NULL AUTO_INCREMENT,
    product_id integer ,
    qty integer,
    seller_id integer,
    user_id integer,
    order_id integer,
    booking_date DATETIME,
    booking_id varchar(255),
    primary key (id)
);

