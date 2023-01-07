CREATE TABLE notification_log(
    id integer NOT NULL AUTO_INCREMENT,
    from_email varchar(255),
    msg BLOB ,
    user_id integer,
    primary key (id)
);

