CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE Role (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(13) NOT NULL
);

CREATE TABLE TypeID (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(50) NOT NULL
);

CREATE TABLE User (
	id int PRIMARY KEY DEFAULT,
	userName VARCHAR(50) NOT NULL,
	email VARCHAR(75) NOT NULL,
	bornDate DATE NOT NULL,
	password Varchar(128) NOT NULL,
    ID_Role UUID REFERENCES Role(id) ON DELETE SET NULL, 
    ID_TypeID UUID REFERENCES TypeID(id) ON DELETE SET NULL
);

INSERT INTO Role (name) VALUES 
    ('Cliente'),
    ('Administrador');

INSERT INTO TypeID (name) VALUES 
    ('Cédula'),
    ('Pasaporte'),
    ('Licencia de conducción'),
    ('Tarjeta de identidad'),
    ('RUT');
