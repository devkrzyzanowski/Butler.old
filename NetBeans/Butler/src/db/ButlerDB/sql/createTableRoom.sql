CREATE TABLE APP.ROOM
(
    idRoom INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(45),
    numberOfBeds INT,
    privateBathroom BOOLEAN,
    CONSTRAINT room_primary_key PRIMARY KEY (idRoom)
);
