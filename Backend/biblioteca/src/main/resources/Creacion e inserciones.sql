/*Creacion esquema donde estan las tablas*/
CREATE SCHEMA juego;

/*Creacion extension para UUID*/
CREATE EXTENSION IF NOT EXISTS pgcrypto;

/*Creacion tabla en el esquema*/
CREATE TABLE juego.role (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(13) NOT NULL
);


CREATE TABLE juego.user (
	id UUID PRIMARY KEY NOT NULL,
	userName VARCHAR(50) NOT NULL,
	email VARCHAR(75) NOT NULL,
	bornDate DATE NOT NULL,
	password Varchar(128) NOT NULL,
    id_role UUID REFERENCES juego.role(id) ON DELETE CASCADE

);

/*Inserta los valores por defecto en la tabla rol*/
INSERT INTO juego.role (name) VALUES
    ('Jugador'),
    ('Administrador');


/*Query para ver la tabla rol*/
select
		id,
		name
from juego.role;

/*Query para ver la tabla user*/
select
		id,
		userName,
		email,
		bornDate,
		password,
		id_role
from juego.user;
