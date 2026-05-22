SET SERVEROUTPUT ON;

BEGIN
    

    UPDATE "Clientes" SET "direccion" = 'Direccion Actualizada' WHERE "id" <= 5;
    UPDATE "Usuarios" SET "rol" = 'Senior' WHERE "id" <= 5;
    UPDATE "Productos" SET "precio" = "precio" + 0.50 WHERE "id" <= 5;
    UPDATE "Ventas" SET "estado" = 'Auditado' WHERE "id" <= 5;
    UPDATE "Detalle Venta" SET "cantidad" = "cantidad" + 5 WHERE "id" <= 5;

    
    DELETE FROM "Detalle Venta" WHERE "id" > 5;
    DELETE FROM "Ventas" WHERE "id" > 5;
    DELETE FROM "Productos" WHERE "id" > 5;
    DELETE FROM "Usuarios" WHERE "id" > 5;
    DELETE FROM "Clientes" WHERE "id" > 5;
    
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Modificación y borrado en cascada ejecutados con éxito.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error crítico durante la modificación/eliminación. Cambios cancelados. Motivo: ' || SQLERRM);
END;
/