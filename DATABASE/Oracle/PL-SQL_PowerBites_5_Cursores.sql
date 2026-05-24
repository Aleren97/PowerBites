SET SERVEROUTPUT ON;

DECLARE
    
    CURSOR c_manual 
    IS SELECT "nombre", "email" 
    FROM "Clientes" 
    WHERE "id" <= 3;

    v_nombre "Clientes"."nombre"%TYPE; 
    v_email "Clientes"."email"%TYPE;

BEGIN
    DBMS_OUTPUT.PUT_LINE(' CURSORES DE CLIENTES ');
    OPEN c_manual;
    LOOP 
        FETCH c_manual INTO v_nombre, v_email; 
        EXIT WHEN c_manual%NOTFOUND; 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Manual: ' || v_nombre || ' (' || v_email || ')'); 
    END LOOP; 
    CLOSE c_manual;
        
    FOR c IN (SELECT "nombre" FROM "Clientes" WHERE LENGTH("nombre") > 10) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Largo: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT "nombre" FROM "Clientes" WHERE "email" LIKE '%gmail%') 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Gmail: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT "nombre" FROM "Clientes" ORDER BY "nombre" DESC) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Descendente: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT "nombre", "telefono" FROM "Clientes" WHERE "telefono" LIKE '%34%') 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Español: ' || c."nombre" || ' - ' || c."telefono" || CHR(10)); 
    END LOOP;

    
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE USUARIOS ');
    FOR c IN (SELECT * FROM "Usuarios") 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Usuario: ' || c."nombre");
    END LOOP;
    
    FOR c IN (SELECT * FROM "Usuarios" WHERE "rol" = 'Senior') 
    LOOP
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Rol Senior: ' || c."nombre");
    END LOOP;
    
    FOR c IN (SELECT * FROM "Usuarios" ORDER BY "email" DESC) 
    LOOP
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Email Desc: ' || c."email");
    END LOOP;
    
    FOR c IN (SELECT * FROM "Usuarios" WHERE "nombre" LIKE '%Comercial%') 
    LOOP
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Es Comercial: ' || c."nombre");
    END LOOP;
    
    FOR c IN (SELECT * FROM "Usuarios" WHERE "id" BETWEEN 2 AND 4) 
    LOOP
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'IDs 2,3,4: ' || c."nombre" || CHR(10));
    END LOOP;

    
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE PRODUCTOS ');
    FOR c IN (SELECT * FROM "Productos") 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Producto: ' || c."nombre" || ' -> ' || c."precio" || '€'); 
    END LOOP;

    FOR c IN (SELECT * FROM "Productos" ORDER BY "precio" DESC) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Más caros: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Productos" WHERE "precio" < 3) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Económicos: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Productos" WHERE "categoria" = 'Vegana') 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Cat. Vegana: ' || c."nombre"); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Productos" WHERE "descripcion" LIKE '%proteina%') 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Con proteína: ' || c."nombre" || CHR(10)); 
    END LOOP;

    
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE VENTAS ');
    FOR c IN (SELECT * FROM "Ventas") 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Venta #' || c."id" || ' -> Total: ' || c."total" || '€'); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Ventas" WHERE "total" > 100) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || '+100€: Venta #' || c."id"); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Ventas" ORDER BY "fecha" ASC) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Por fecha: ' || TO_CHAR(c."fecha", 'DD/MM/YYYY')); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Ventas" WHERE "estado" = 'Auditado') 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Auditadas: Venta #' || c."id"); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Ventas" WHERE "cliente id" = 1) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Del Cliente 1: Venta #' || c."id" || CHR(10)); 
    END LOOP;

    
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' CURSORES DE DETALLE VENTA ');
    FOR c IN (SELECT * FROM "Detalle Venta") 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Línea Detalle #' || c."id"); 
    END LOOP;

    FOR c IN (SELECT * FROM "Detalle Venta" WHERE "cantidad" > 20) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Volumen Alto (>20): Detalle #' || c."id"); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Detalle Venta" WHERE "venta id" = 1) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Pertenecen a Venta 1: Prod. ID ' || c."producto id"); 
    END LOOP;
    
    FOR c IN (SELECT * FROM "Detalle Venta" ORDER BY "cantidad" DESC) 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Mayor cantidad: ' || c."cantidad" || ' unidades'); 
    END LOOP;
    
    FOR c IN (SELECT "id", ("cantidad" * "precio unitario") AS subtotal FROM "Detalle Venta") 
    LOOP 
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Cálculo Subtotal Detalle #' || c."id" || ' -> ' || c.subtotal || '€'); 
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Completado: 25 cursores ejecutados.');
END;
/