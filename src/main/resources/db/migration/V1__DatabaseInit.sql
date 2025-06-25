CREATE TABLE manufacturers (
    id uuid NOT NULL PRIMARY KEY,
    name varchar(60) NOT NULL
);

CREATE TABLE models (
    id uuid NOT NULL PRIMARY KEY,
    name varchar(50) NOT NULL,
    manufacturer_id uuid NOT NULL,
    CONSTRAINT fk_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id)
);

CREATE TABLE vehicles (
    id uuid NOT NULL PRIMARY KEY,
    manufacturer_id uuid NOT NULL,
    model_id uuid NOT NULL,
    production_year SMALLINT NOT NULL,
    fuel varchar(30) NOT NULL,
    gearbox varchar(40) NOT NULL,
    engine_size SMALLINT NOT NULL,
    horse_power SMALLINT NOT NULL,
    license_plate varchar(10),
    vin varchar(20) NOT NULL,
    is_first_owner boolean NOT NULL,
    is_first_owner_in_poland boolean NOT NULL
);

CREATE TABLE motor_vehicles (
    id uuid NOT NULL PRIMARY KEY,
    distance BIGINT NOT NULL,
    CONSTRAINT fk_vehicle FOREIGN KEY (id) REFERENCES vehicles(id)
);

CREATE TABLE cars (
    id uuid NOT NULL PRIMARY KEY,
    doors_amount int NOT NULL,
    CONSTRAINT fk_motor_vehicle FOREIGN KEY (id) REFERENCES motor_vehicles(id)
);