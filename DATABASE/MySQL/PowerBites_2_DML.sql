use powerbites;

-- CLIENTES
INSERT INTO `Clientes` (`nombre`, `email`, `telefono`, `direccion`) VALUES 
('Gimnasio FitLife', 'contacto@fitlife.com', '600111222', 'Calle Falsa 123'),
('NutriSport S.L.', 'ventas@nutrisport.es', '600333444', 'Avenida Central 45'),
('CrossFit Titan', 'info@titancf.com', '600555666', 'Nave 3, Poligono Ind.'),
('Supermercado Sano', 'compras@sano.com', '600777888', 'Plaza Mayor 1'),
('Carlos Runner', 'carlos@mail.com', '600999000', 'Calle Corredores 4'),
('Emirates Fitness', 'info@emiratesfit.com', '611222333', 'London Colney 1'),
('Gunners Nutrition', 'ventas@gunners.co.uk', '611444555', 'Highbury Square'),
('Cannon Crossfit', 'box@cannoncf.com', '611666777', 'Arteta Avenue'),
('North London Sports', 'nls@sports.com', '611888999', 'Islington 14'),
('Invincibles Gym', 'invincibles@gym.com', '611000111', 'Trophy Room 49');

-- USUARIOS
INSERT INTO `Usuarios` (`nombre`, `email`, `rol`, `password hash`) VALUES 
('Laura Comercial', 'laura@powerbites.com', 'Ventas', 'hash123'),
('Pedro Distribucion', 'pedro@powerbites.com', 'Logistica', 'hash456'),
('Admin General', 'admin@powerbites.com', 'Administrador', 'hash789'),
('Sofia Ventas', 'sofia@powerbites.com', 'Ventas', 'hash321'),
('Javier Soporte', 'javier@powerbites.com', 'Soporte', 'hash654'),
('Martin Odegaard', 'martin@powerbites.com', 'Ventas', 'hash10'),
('Bukayo Saka', 'bukayo@powerbites.com', 'Logistica', 'hash07'),
('Declan Rice', 'declan@powerbites.com', 'Soporte', 'hash41'),
('William Saliba', 'william@powerbites.com', 'Seguridad', 'hash02'),
('David Raya', 'david@powerbites.com', 'Administrador', 'hash22');

-- PRODUCTOS
INSERT INTO `Productos` (`nombre`, `descripcion`, `precio`, `categoria`) VALUES 
('PowerBite Choco', 'Barrita 20g proteina', 2.50, 'Proteina'),
('EnergyCore', 'Avena y frutos rojos', 1.80, 'Carbohidratos'),
('VeganBite', 'Sin gluten, vegana', 2.00, 'Vegana'),
('PreWorkout', 'Con cafeina extra', 3.00, 'Pre-entreno'),
('Recovery Almond', 'Almendras y miel', 2.20, 'Recuperacion'),
('Gunner Drink', 'Bebida isotonica', 1.50, 'Hidratacion'),
('Red Cannon', 'Proteina suero', 35.00, 'Suplemento'),
('Emirates Pre', 'Explosion energia', 28.50, 'Pre-entreno'),
('North London BCAA', 'Recuperacion', 22.00, 'Recuperacion'),
('Invincibles Bar', 'Barrita oro', 3.50, 'Carbohidratos');

-- VENTAS
INSERT INTO `Ventas` (`cliente id`, `usuario id`, `fecha`, `estado`, `total`) VALUES 
(1, 1, '2023-10-01', 'Completado', 125.00),
(2, 4, '2023-10-02', 'Pendiente', 90.00),
(3, 1, '2023-10-03', 'Enviado', 150.00),
(4, 4, '2023-10-04', 'Completado', 44.00),
(5, 1, '2023-10-05', 'Cancelado', 20.00),
(6, 6, '2023-10-06', 'Completado', 200.00),
(7, 7, '2023-10-07', 'Enviado', 350.00),
(8, 8, '2023-10-08', 'Pendiente', 120.00),
(9, 9, '2023-10-09', 'Completado', 85.00),
(10, 10, '2023-10-10', 'Pendiente', 400.00);

-- DETALLE VENTA
INSERT INTO `Detalle Venta` (`venta id`, `producto id`, `cantidad`, `precio unitario`) VALUES 
(1, 1, 50, 2.50),
(2, 2, 50, 1.80),
(3, 4, 50, 3.00),
(4, 5, 20, 2.20),
(5, 3, 10, 2.00),
(6, 6, 100, 1.50),
(7, 7, 10, 35.00),
(8, 8, 5, 28.50),
(9, 9, 4, 22.00),
(10, 10, 115, 3.50);