CREATE TABLE product(
    id integer NOT NULL AUTO_INCREMENT,
    name varchar(255),
    description varchar(4000),
    img_url varchar(3000),
    primary key (id)
);
-- data
INSERT INTO product (id, name,description,img_url) VALUES (10001, 'iPhone','The iPhone is a smartphone made by Apple that combines a computer, iPod, digital camera and cellular phone into one device with a touchscreen interface. The iPhone runs the iOS operating system, and in 2021 when the iPhone 13 was introduced, it offered up to 1 TB of storage and a 12-megapixel camera.','https://m.media-amazon.com/images/I/71cQWYVtcBL._SX679_.jpg');
INSERT INTO product (id, name,description,img_url) VALUES (10002, 'Motorola','Motorola develops analog and digital two-way radio, voice and data communications products and systems, mobile computing, advanced data capture, wireless infrastructure and RFID solutions to customers worldwide.','https://m.media-amazon.com/images/I/71y5je-UISL._AC_UY218_.jpg');
INSERT INTO product (id, name,description,img_url) VALUES (10003, 'Samsung','Samsung, South Korean company that is one of the world s largest producers of electronic devices. Samsung specializes in the production of a wide variety of consumer and industry electronics, including appliances, digital media devices, semiconductors, memory chips, and integrated systems.','https://m.media-amazon.com/images/I/81-fFXQdPTL._AC_UY218_.jpg');
INSERT INTO product (id, name,description,img_url) VALUES (10004, 'Nokia','Nokia Corp (Nokia) is a communications and information technology company that operates in the areas of network infrastructure and advanced technologies. It offers fixed networks, mobile phones, WiFi systems, IP routing, subscriber data management, network implementation, network modernization, IoT, and 5G services.','https://m.media-amazon.com/images/I/71x+m2-yb7L._AC_UY218_.jpg');


