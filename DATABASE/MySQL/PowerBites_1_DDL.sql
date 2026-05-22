CREATE DATABASE IF NOT EXISTS powerbites;
USE powerbites;


DROP TABLE IF EXISTS `Detalle Venta`;
DROP TABLE IF EXISTS `Ventas`;
DROP TABLE IF EXISTS `Productos`;
DROP TABLE IF EXISTS `Usuarios`;
DROP TABLE IF EXISTS `Clientes`;


CREATE TABLE `Clientes` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `telefono` VARCHAR(15) NOT NULL UNIQUE,
    `direccion` VARCHAR(200) NOT NULL
);

CREATE TABLE `Usuarios` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `rol` VARCHAR(50),
    `password hash` VARCHAR(255) NOT NULL
);

CREATE TABLE `Productos` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(100) NOT NULL UNIQUE,
    `descripcion` VARCHAR(255),
    `precio` DECIMAL(10,2) NOT NULL,
    `categoria` VARCHAR(50)
);

CREATE TABLE `Ventas` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `cliente id` INT NOT NULL,
    `usuario id` INT NOT NULL,
    `fecha` DATE NOT NULL,
    `estado` VARCHAR(50) NOT NULL,
    `total` DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (`cliente id`) REFERENCES `Clientes`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`usuario id`) REFERENCES `Usuarios`(`id`) ON DELETE CASCADE
);

CREATE TABLE `Detalle Venta` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `venta id` INT NOT NULL,
    `producto id` INT NOT NULL,
    `cantidad` INT NOT NULL,
    `precio unitario` DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (`venta id`) REFERENCES `Ventas`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`producto id`) REFERENCES `Productos`(`id`) ON DELETE CASCADE
);