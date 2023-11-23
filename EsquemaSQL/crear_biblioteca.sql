CREATE USER 'admin'@localhost IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';

CREATE DATABASE IF NOT EXISTS BIBLIOTECA;
USE BIBLIOTECA;

CREATE TABLE IF NOT EXISTS SOCIOS
(DNI varchar(9),
NOMBRE varchar(60),
APELLIDOS varchar(60),
PRIMARY KEY (DNI));

CREATE TABLE IF NOT EXISTS LIBROS
(ISBN varchar(13),
TITULO varchar(60),
AUTOR varchar(60),
ALQUILADO boolean,
PRIMARY KEY (ISBN));

CREATE TABLE IF NOT EXISTS ALQUILERES
(ID_ALQUILER int auto_increment,
ISBN_LIBRO varchar(13),
DNI_SOCIO varchar(9),
FECHA_ALQUILER datetime,
FECHA_DEVOLUCION datetime,
FOREIGN KEY(ISBN_LIBRO) REFERENCES LIBROS(ISBN),
FOREIGN KEY(DNI_SOCIO) REFERENCES SOCIOS(DNI),
PRIMARY KEY(ID_ALQUILER));

INSERT INTO SOCIOS (DNI,nombre,apellidos) VALUES ('39490438J','Gonzalo','Campos Domínguez');
INSERT INTO SOCIOS (DNI,nombre,apellidos) VALUES ('35578855V','Nerea','Martínez Fernández');
INSERT INTO SOCIOS (DNI,nombre,apellidos) VALUES ('36138129T','Daniel','Campos Rocha');

INSERT INTO LIBROS (ISBN,titulo,autor,alquilado) VALUES ('8438231039423','El Quijote','Miguel de cervantes',false);
INSERT INTO LIBROS (ISBN,titulo,autor,alquilado) VALUES ('1435231033426','La niebla','Stephen King',true);
INSERT INTO LIBROS (ISBN,titulo,autor,alquilado) VALUES ('3438231039421','Cantares Galegos','Rosalía de Castro',false);
