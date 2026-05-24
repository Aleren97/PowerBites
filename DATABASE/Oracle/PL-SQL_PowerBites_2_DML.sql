SET SERVEROUTPUT ON;

BEGIN
    
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (1, 'Gimnasio FitLife', 'contacto@fitlife.com', '600111222', 'Calle Falsa 123');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (2, 'NutriSport S.L.', 'ventas@nutrisport.es', '600333444', 'Avenida Central 45');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (3, 'CrossFit Titan', 'info@titancf.com', '600555666', 'Nave 3, Poligono Ind.');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (4, 'Supermercado Sano', 'compras@sano.com', '600777888', 'Plaza Mayor 1');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (5, 'Carlos Runner', 'carlos@mail.com', '600999000', 'Calle Corredores 4');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (6, 'Emirates Fitness', 'info@emiratesfit.com', '611222333', 'London Colney 1');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (7, 'Gunners Nutrition', 'ventas@gunners.co.uk', '611444555', 'Highbury Square');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (8, 'Cannon Crossfit', 'box@cannoncf.com', '611666777', 'Arteta Avenue');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (9, 'North London Sports', 'nls@sports.com', '611888999', 'Islington 14');
    INSERT INTO "Clientes" ("id", "nombre", "email", "telefono", "direccion") VALUES (10, 'Invincibles Gym', 'invincibles@gym.com', '611000111', 'Trophy Room 49');

    
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (1, 'Laura Comercial', 'laura@powerbites.com', 'Ventas', 'hash123');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (2, 'Pedro Distribucion', 'pedro@powerbites.com', 'Logistica', 'hash456');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (3, 'Admin General', 'admin@powerbites.com', 'Administrador', 'hash789');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (4, 'Sofia Ventas', 'sofia@powerbites.com', 'Ventas', 'hash321');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (5, 'Javier Soporte', 'javier@powerbites.com', 'Soporte', 'hash654');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (6, 'Martin Odegaard', 'martin@powerbites.com', 'Ventas', 'hash10');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (7, 'Bukayo Saka', 'bukayo@powerbites.com', 'Logistica', 'hash07');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (8, 'Declan Rice', 'declan@powerbites.com', 'Soporte', 'hash41');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (9, 'William Saliba', 'william@powerbites.com', 'Seguridad', 'hash02');
    INSERT INTO "Usuarios" ("id", "nombre", "email", "rol", "password hash") VALUES (10, 'David Raya', 'david@powerbites.com', 'Administrador', 'hash22');

    
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (1, 'PowerBite Choco', 'Barrita 20g proteina', 2.50, 'Proteina');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (2, 'EnergyCore', 'Avena y frutos rojos', 1.80, 'Carbohidratos');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (3, 'VeganBite', 'Sin gluten, vegana', 2.00, 'Vegana');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (4, 'PreWorkout', 'Con cafeina extra', 3.00, 'Pre-entreno');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (5, 'Recovery Almond', 'Almendras y miel', 2.20, 'Recuperacion');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (6, 'Gunner Drink', 'Bebida isotonica', 1.50, 'Hidratacion');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (7, 'Red Cannon', 'Proteina suero', 35.00, 'Suplemento');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (8, 'Emirates Pre', 'Explosion energia', 28.50, 'Pre-entreno');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (9, 'North London BCAA', 'Recuperacion', 22.00, 'Recuperacion');
    INSERT INTO "Productos" ("id", "nombre", "descripcion", "precio", "categoria") VALUES (10, 'Invincibles Bar', 'Barrita oro', 3.50, 'Carbohidratos');

     
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (1, 1, 1, DATE '2023-10-01', 'Completado', 125.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (2, 2, 4, DATE '2023-10-02', 'Pendiente', 90.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (3, 3, 1, DATE '2023-10-03', 'Enviado', 150.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (4, 4, 4, DATE '2023-10-04', 'Completado', 44.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (5, 5, 1, DATE '2023-10-05', 'Cancelado', 20.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (6, 6, 6, DATE '2023-10-06', 'Completado', 200.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (7, 7, 7, DATE '2023-10-07', 'Enviado', 350.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (8, 8, 8, DATE '2023-10-08', 'Pendiente', 120.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (9, 9, 9, DATE '2023-10-09', 'Completado', 85.00);
    INSERT INTO "Ventas" ("id", "cliente id", "usuario id", "fecha", "estado", "total") VALUES (10, 10, 10, DATE '2023-10-10', 'Pendiente', 400.00);

    
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (1, 1, 1, 50, 2.50);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (2, 2, 2, 50, 1.80);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (3, 3, 4, 50, 3.00);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (4, 4, 5, 20, 2.20);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (5, 5, 3, 10, 2.00);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (6, 6, 6, 100, 1.50);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (7, 7, 7, 10, 35.00);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (8, 8, 8, 5, 28.50);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (9, 9, 9, 4, 22.00);
    INSERT INTO "Detalle Venta" ("id", "venta id", "producto id", "cantidad", "precio unitario") VALUES (10, 10, 10, 115, 3.50);


    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Script 2: Registros base insertados con éxito.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error crítico durante la inserción. Cambios cancelados. Motivo: ' || SQLERRM);
END;
/