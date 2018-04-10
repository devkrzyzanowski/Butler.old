DROP TABLE APP.ROOM;
CREATE TABLE APP.ROOM
(
    idRoom INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    room_name VARCHAR(45),
    number_of_single_beds INT,
    number_of_double_beds INT,
    number_of_extra_beds INT,
    floor_number INT,
    price_of_room DOUBLE,
    price_of_adult DOUBLE,
    price_of_minor DOUBLE,
    small_description VARCHAR(45),
    big_description VARCHAR(255),
    extra_description VARCHAR(45),
    building VARCHAR(45),
    CONSTRAINT room_primary_key PRIMARY KEY (idRoom)
);