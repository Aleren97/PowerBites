SET SERVEROUTPUT ON;

DROP TABLE "Detalle Venta" CASCADE CONSTRAINTS;
DROP TABLE "Ventas" CASCADE CONSTRAINTS;
DROP TABLE "Productos" CASCADE CONSTRAINTS;
DROP TABLE "Usuarios" CASCADE CONSTRAINTS;
DROP TABLE "Clientes" CASCADE CONSTRAINTS;


CREATE TABLE "Clientes" (
    "id" NUMBER PRIMARY KEY,
    "nombre" VARCHAR2(100) NOT NULL,
    "email" VARCHAR2(100) NOT NULL UNIQUE,
    "telefono" VARCHAR2(15) NOT NULL UNIQUE,
    "direccion" VARCHAR2(200) NOT NULL
);

CREATE TABLE "Usuarios" (
    "id" NUMBER PRIMARY KEY,
    "nombre" VARCHAR2(100) NOT NULL,
    "email" VARCHAR2(100) NOT NULL UNIQUE,
    "rol" VARCHAR2(50),
    "password hash" VARCHAR2(255) NOT NULL
);

CREATE TABLE "Productos" (
    "id" NUMBER PRIMARY KEY,
    "nombre" VARCHAR2(100) NOT NULL UNIQUE,
    "descripcion" VARCHAR2(255),
    "precio" NUMBER(10,2) NOT NULL,
    "categoria" VARCHAR2(50)
);

CREATE TABLE "Ventas" (
    "id" NUMBER PRIMARY KEY,
    "cliente id" NUMBER NOT NULL,
    "usuario id" NUMBER NOT NULL,
    "fecha" DATE NOT NULL,
    "estado" VARCHAR2(50) NOT NULL,
    "total" NUMBER(10,2) NOT NULL,
    CONSTRAINT fk_ventas_clientes FOREIGN KEY ("cliente id") REFERENCES "Clientes"("id"),
    CONSTRAINT fk_ventas_usuarios FOREIGN KEY ("usuario id") REFERENCES "Usuarios"("id")
);

CREATE TABLE "Detalle Venta" (
    "id" NUMBER PRIMARY KEY,
    "venta id" NUMBER NOT NULL,
    "producto id" NUMBER NOT NULL,
    "cantidad" NUMBER NOT NULL,
    "precio unitario" NUMBER(10,2) NOT NULL,
    CONSTRAINT fk_detalle_ventas FOREIGN KEY ("venta id") REFERENCES "Ventas"("id"),
    CONSTRAINT fk_detalle_productos FOREIGN KEY ("producto id") REFERENCES "Productos"("id")
);