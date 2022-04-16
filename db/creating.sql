CREATE TABLE IF NOT EXISTS houses (
    id INT AUTO_INCREMENT,
    count_flats_on_floor INT NOT NULL,
    CONSTRAINT houses_pk PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS floors (
    id INT AUTO_INCREMENT,
    house_id INT NOT NULL,
    CONSTRAINT floors_pk PRIMARY KEY (id),
    CONSTRAINT floors_houses_id_fk FOREIGN KEY (house_id)
        REFERENCES houses (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS apartments (
    id INT AUTO_INCREMENT,
    count_people INT NOT NULL,
    count_rooms INT NOT NULL,
    area DOUBLE NOT NULL,
    floor_id INT NOT NULL,
    CONSTRAINT apartments_pk PRIMARY KEY (id),
    CONSTRAINT apartments_floors_id_fk FOREIGN KEY (floor_id)
        REFERENCES floors (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
