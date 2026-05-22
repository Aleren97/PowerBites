SET SERVEROUTPUT ON;

DECLARE
    -- 1. Ejemplo de cursor explícito manual (aplicando la teoría del ciclo de vida)
    CURSOR c_manual IS SELECT "nombre", "email" FROM "Clientes" WHERE "id" <= 3;
    v_nombre "Clientes"."nombre"%TYPE; 
    v_email "Clientes"."email"%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE(' CURSORES DE CLIENTES ');
    
    -- Apertura, lectura y cierre manual
    OPEN c_manual; 
    LOOP 
        FETCH c_manual INTO v_nombre, v_email; 
        EXIT WHEN c_manual%NOTFOUND; 
        DBMS_OUTPUT.PUT_LINE('Manual: ' || v_nombre || ' (' || v_email || ')'); 
    END LOOP; 
    CLOSE c_manual;
    
    -- Los demás cursores utilizan el Bucle FOR (Automatiza apertura, lectura y cierre)
    FOR c IN (SELECT "nombre" FROM "Clientes" WHERE LENGTH("nombre") > 10) LOOP 
        DBMS_OUTPUT.PUT_LINE('Largo: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT "nombre" FROM "Clientes" WHERE "email" LIKE '%gmail%') LOOP 
        DBMS_OUTPUT.PUT_LINE('Gmail: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT "nombre" FROM "Clientes" ORDER BY "nombre" DESC) LOOP 
        DBMS_OUTPUT.PUT_LINE('Descendente: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT "nombre", "telefono" FROM "Clientes" WHERE "telefono" LIKE '%34%') LOOP 
        DBMS_OUTPUT.PUT_LINE('Español: ' || c."nombre" || ' - ' || c."telefono"); 
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE USUARIOS ');
    FOR c IN (SELECT * FROM "Usuarios") LOOP DBMS_OUTPUT.PUT_LINE('Usuario: ' || c."nombre"); END LOOP;
    FOR c IN (SELECT * FROM "Usuarios" WHERE "rol" = 'Senior') LOOP DBMS_OUTPUT.PUT_LINE('Rol Senior: ' || c."nombre"); END LOOP;
    FOR c IN (SELECT * FROM "Usuarios" ORDER BY "email" DESC) LOOP DBMS_OUTPUT.PUT_LINE('Email Desc: ' || c."email"); END LOOP;
    FOR c IN (SELECT * FROM "Usuarios" WHERE "nombre" LIKE '%Comercial%') LOOP DBMS_OUTPUT.PUT_LINE('Es Comercial: ' || c."nombre"); END LOOP;
    FOR c IN (SELECT * FROM "Usuarios" WHERE "id" BETWEEN 2 AND 4) LOOP DBMS_OUTPUT.PUT_LINE('IDs 2,3,4: ' || c."nombre"); END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE PRODUCTOS ');
    FOR c IN (SELECT * FROM "Productos") LOOP DBMS_OUTPUT.PUT_LINE('Producto: ' || c."nombre" || ' -> ' || c."precio" || '€'); END LOOP;
    FOR c IN (SELECT * FROM "Productos" ORDER BY "precio" DESC) LOOP DBMS_OUTPUT.PUT_LINE('Más caros: ' || c."nombre"); END LOOP;
    FOR c IN (SELECT * FROM "Productos" WHERE "precio" < 3) LOOP DBMS_OUTPUT.PUT_LINE('Económicos: ' || c."nombre"); END LOOP;
    FOR c IN (SELECT * FROM "Productos" WHERE "categoria" = 'Vegana') LOOP DBMS_OUTPUT.PUT_LINE('Cat. Vegana: ' || c."nombre"); END LOOP;
    FOR c IN (SELECT * FROM "Productos" WHERE "descripcion" LIKE '%proteina%') LOOP DBMS_OUTPUT.PUT_LINE('Con proteína: ' || c."nombre"); END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE VENTAS ');
    FOR c IN (SELECT * FROM "Ventas") LOOP DBMS_OUTPUT.PUT_LINE('Venta #' || c."id" || ' -> Total: ' || c."total" || '€'); END LOOP;
    FOR c IN (SELECT * FROM "Ventas" WHERE "total" > 100) LOOP DBMS_OUTPUT.PUT_LINE('+100€: Venta #' || c."id"); END LOOP;
    FOR c IN (SELECT * FROM "Ventas" ORDER BY "fecha" ASC) LOOP DBMS_OUTPUT.PUT_LINE('Por fecha: ' || TO_CHAR(c."fecha", 'DD/MM/YYYY')); END LOOP;
    FOR c IN (SELECT * FROM "Ventas" WHERE "estado" = 'Auditado') LOOP DBMS_OUTPUT.PUT_LINE('Auditadas: Venta #' || c."id"); END LOOP;
    FOR c IN (SELECT * FROM "Ventas" WHERE "cliente id" = 1) LOOP DBMS_OUTPUT.PUT_LINE('Del Cliente 1: Venta #' || c."id"); END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE DETALLE VENTA ');
    FOR c IN (SELECT * FROM "Detalle Venta") LOOP DBMS_OUTPUT.PUT_LINE('Línea Detalle #' || c."id"); END LOOP;
    FOR c IN (SELECT * FROM "Detalle Venta" WHERE "cantidad" > 20) LOOP DBMS_OUTPUT.PUT_LINE('Volumen Alto (>20): Detalle #' || c."id"); END LOOP;
    FOR c IN (SELECT * FROM "Detalle Venta" WHERE "venta id" = 1) LOOP DBMS_OUTPUT.PUT_LINE('Pertenecen a Venta 1: Prod. ID ' || c."producto id"); END LOOP;
    FOR c IN (SELECT * FROM "Detalle Venta" ORDER BY "cantidad" DESC) LOOP DBMS_OUTPUT.PUT_LINE('Mayor cantidad: ' || c."cantidad" || ' unidades'); END LOOP;
    FOR c IN (SELECT "id", ("cantidad" * "precio unitario") AS subtotal FROM "Detalle Venta") LOOP DBMS_OUTPUT.PUT_LINE('Cálculo Subtotal Detalle #' || c."id" || ' -> ' || c.subtotal || '€'); END LOOP;
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Completado: 25 cursores ejecutados.');
END;
/