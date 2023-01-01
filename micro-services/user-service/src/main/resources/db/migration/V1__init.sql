CREATE TABLE user(
    id integer NOT NULL,
    email varchar(255),
    fname varchar(255),
    lname varchar(255),
    last_login_time TIMESTAMP,
    primary key (id),
    unique(email)
);
-- data
INSERT INTO user (id, email,fname,lname) VALUES (50001, 'nityaay8@gmail.com','nityaay','n');
