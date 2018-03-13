CREATE TABLE APP.OPERATION
    (ID INT PRIMARY KEY,
    DBUser_idDBUser INT NOT NULL,
    operation VARCHAR(45),
    date TIMESTAMP
);
ALTER TABLE APP.OPERATION
ADD FOREIGN KEY (DBUser_idDBUser)
REFERENCES APP.DBUSER(idDBUser)

