CREATE TABLE account(
    id integer NOT NULL AUTO_INCREMENT,
    amount DOUBLE,
    user_id integer,
    primary key (id)
);
-- data
INSERT INTO account (id, amount,user_id) VALUES (60001, 4000,50001);
-- log
CREATE TABLE account_log(
    id integer NOT NULL AUTO_INCREMENT,
    balance DOUBLE,
    tx_amount DOUBLE,
    type varchar(255),
    user_id integer,
    primary key (id)
);
