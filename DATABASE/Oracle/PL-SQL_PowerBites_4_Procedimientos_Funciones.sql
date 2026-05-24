SET SERVEROUTPUT ON;

--  CLIENTES
CREATE OR REPLACE PROCEDURE añadir_prefijo AS
BEGIN
    UPDATE "Clientes" 
    SET "telefono" = CONCAT('+34 ', "telefono") 
    WHERE "telefono" NOT LIKE '+34%';
    
    IF SQL%FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Se han actualizado ' || SQL%ROWCOUNT || ' teléfonos.');
    END IF;
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN 
        ROLLBACK; 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE PROCEDURE auditar_correos AS
    CURSOR c_cli IS SELECT "nombre", "email" 
    FROM "Clientes";
    v_n "Clientes"."nombre"%TYPE; 
    v_e "Clientes"."email"%TYPE;

BEGIN
    OPEN c_cli;
    LOOP
        FETCH c_cli INTO v_n, v_e;
        EXIT WHEN c_cli%NOTFOUND;
        
        IF INSTR(v_e, '@') > 0 THEN 
            DBMS_OUTPUT.PUT_LINE(v_n || ': Correo Válido'); 
        ELSE 
            DBMS_OUTPUT.PUT_LINE(v_n || ': INVÁLIDO'); 
        END IF;
    END LOOP;
    CLOSE c_cli;

EXCEPTION
    WHEN OTHERS THEN 
        IF c_cli%ISOPEN THEN 
            CLOSE c_cli; 
        END IF; 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE FUNCTION contar_dominios(p_dom IN VARCHAR2) RETURN NUMBER AS
    v_tot NUMBER;

BEGIN
    SELECT COUNT(*) INTO v_tot 
    FROM "Clientes" 
    WHERE "email" LIKE '%' || p_dom || '%';
    RETURN NVL(v_tot, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/


CREATE OR REPLACE FUNCTION max_longitud_direccion RETURN NUMBER AS
    v_max NUMBER;

BEGIN
    SELECT MAX(LENGTH("direccion")) INTO v_max 
    FROM "Clientes";
    RETURN NVL(v_max, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/




--  USUARIOS
CREATE OR REPLACE PROCEDURE actualizar_rol_vacio AS
BEGIN
    UPDATE "Usuarios" SET "rol" = 'Admin_Temp' 
    WHERE "rol" IS NULL;
    
    IF SQL%FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Roles actualizados: ' || SQL%ROWCOUNT); 
    END IF;
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN 
        ROLLBACK; 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE PROCEDURE auditar_passwords AS
BEGIN
    FOR u IN (SELECT "nombre", "password hash" FROM "Usuarios") LOOP
        IF LENGTH(u."password hash") < 8 THEN 
            DBMS_OUTPUT.PUT_LINE('Alerta Seguridad: ' || u."nombre"); 
        END IF;
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE FUNCTION contar_rol(p_rol VARCHAR2) RETURN NUMBER AS
    v_c NUMBER;

BEGIN
    SELECT COUNT(*) INTO v_c 
    FROM "Usuarios" WHERE "rol" = p_rol;
    RETURN NVL(v_c, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/

CREATE OR REPLACE FUNCTION existe_email_usuario(p_email VARCHAR2) RETURN NUMBER AS
    v_c NUMBER;

BEGIN
    SELECT COUNT(*) INTO v_c 
    FROM "Usuarios" 
    WHERE "email" = p_email;
    
    IF v_c > 0 THEN 
        RETURN 1; 
    ELSE 
        RETURN 0; 
    END IF;

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/




--  PRODUCTOS
CREATE OR REPLACE PROCEDURE descuento_categoria(p_cat VARCHAR2, p_desc NUMBER) AS
BEGIN
    UPDATE "Productos" SET "precio" = "precio" - p_desc 
    WHERE "categoria" = p_cat;
    
    IF SQL%FOUND THEN 
        DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || ' productos rebajados.'); 
    END IF;
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN 
        ROLLBACK; 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE PROCEDURE auditar_productos_premium AS
BEGIN
    FOR p IN (SELECT "nombre", "precio" FROM "Productos") LOOP
        IF p."precio" >= 20.00 THEN 
            DBMS_OUTPUT.PUT_LINE('Premium: ' || p."nombre"); 
        END IF;
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE FUNCTION precio_medio_cat(p_cat VARCHAR2) RETURN NUMBER AS
    v_avg NUMBER;

BEGIN
    SELECT AVG("precio") INTO v_avg 
    FROM "Productos" 
    WHERE "categoria" = p_cat;
    RETURN NVL(ROUND(v_avg, 2), 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/


CREATE OR REPLACE FUNCTION precio_maximo RETURN NUMBER AS
    v_max NUMBER;

BEGIN
    SELECT MAX("precio") INTO v_max 
    FROM "Productos";
    RETURN NVL(v_max, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/




--  VENTAS
CREATE OR REPLACE PROCEDURE cancelar_pendientes AS
BEGIN
    UPDATE "Ventas" SET "estado" = 'Cancelado' 
    WHERE "estado" = 'Pendiente';
    
    IF SQL%FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Canceladas ' || SQL%ROWCOUNT || ' ventas.'); 
    END IF;
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN 
        ROLLBACK; 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE PROCEDURE auditar_ventas_bajas AS
    CURSOR c_ventas IS SELECT "id", "total" 
    FROM "Ventas";
    v_id NUMBER; 
    v_tot NUMBER;

BEGIN
    OPEN c_ventas;
    LOOP
        FETCH c_ventas INTO v_id, v_tot;
        EXIT WHEN c_ventas%NOTFOUND;
        
        IF v_tot < 50.00 THEN 
            DBMS_OUTPUT.PUT_LINE('Revisar Venta #' || v_id);
        END IF;
    END LOOP;
    CLOSE c_ventas;

EXCEPTION
    WHEN OTHERS THEN 
        IF c_ventas%ISOPEN THEN 
            CLOSE c_ventas; 
        END IF; 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE FUNCTION ingresos_por_estado(p_estado VARCHAR2) RETURN NUMBER AS
    v_sum NUMBER;

BEGIN
    SELECT SUM("total") INTO v_sum 
    FROM "Ventas" 
    WHERE "estado" = p_estado;
    RETURN NVL(v_sum, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/


CREATE OR REPLACE FUNCTION ventas_por_usuario(p_user NUMBER) RETURN NUMBER AS
    v_c NUMBER;

BEGIN
    SELECT COUNT(*) INTO v_c 
    FROM "Ventas" 
    WHERE "usuario id" = p_user;
    RETURN NVL(v_c, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/




--  DETALLE VENTA
CREATE OR REPLACE PROCEDURE subir_precio_minorista AS
BEGIN
    UPDATE "Detalle Venta" SET "precio unitario" = "precio unitario" + 1.00 
    WHERE "cantidad" < 10;
    
    IF SQL%FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Ajustados ' || SQL%ROWCOUNT || ' detalles.'); 
    END IF;
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN 
        ROLLBACK; 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE PROCEDURE auditar_cantidades AS
BEGIN
    FOR d IN (SELECT "id", "cantidad" FROM "Detalle Venta") LOOP
        IF d."cantidad" > 50 THEN 
            DBMS_OUTPUT.PUT_LINE('Detalle #' || d."id" || ' Mayorista.'); 
        END IF;
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE FUNCTION uds_vendidas_producto(p_prod NUMBER) RETURN NUMBER AS
    v_sum NUMBER;

BEGIN
    SELECT SUM("cantidad") INTO v_sum 
    FROM "Detalle Venta" 
    WHERE "producto id" = p_prod;
    RETURN NVL(v_sum, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/


CREATE OR REPLACE FUNCTION calcular_subtotal_detalle(p_det NUMBER) RETURN NUMBER AS
    v_sub NUMBER;

BEGIN
    SELECT ("cantidad" * "precio unitario") INTO v_sub 
    FROM "Detalle Venta" 
    WHERE "id" = p_det;
    RETURN NVL(v_sub, 0);

EXCEPTION
    WHEN OTHERS THEN 
        RETURN -1;
END;
/