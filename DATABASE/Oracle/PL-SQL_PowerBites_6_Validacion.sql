SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE(' TEST DE PROCEDIMIENTOS ');
    
    añadir_prefijo;
    auditar_correos;
    actualizar_rol_vacio;
    descuento_categoria('Proteina', 0.20);
    cancelar_pendientes;
    subir_precio_minorista;
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || ' TEST DE FUNCIONES (RETORNOS) ');
    
    DBMS_OUTPUT.PUT_LINE('Total Clientes con Gmail: ' || contar_dominios('gmail.com'));
    DBMS_OUTPUT.PUT_LINE('Longitud máxima de una dirección: ' || max_longitud_direccion || ' caracteres');
    DBMS_OUTPUT.PUT_LINE('Total Usuarios en Ventas: ' || contar_rol('Ventas'));
    DBMS_OUTPUT.PUT_LINE('Precio Máximo en la tienda: ' || precio_maximo || '€');
    DBMS_OUTPUT.PUT_LINE('Total Ingresos de ventas auditadas: ' || ingresos_por_estado('Auditado') || '€');
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Validación de PL/SQL superada.');
END;
/