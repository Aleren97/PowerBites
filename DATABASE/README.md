# Proyecto CRM PowerBites - Módulo de Base de Datos

Este directorio contiene todos los scripts necesarios para la creación, manipulación y lógica de negocio de la base de datos del CRM PowerBites. El proyecto está dividido en un entorno Oracle (para la lógica PL/SQL avanzada) y un entorno MySQL (para la persistencia de datos de la aplicación Java).

---

## Requisitos Previos

Para ejecutar este proyecto, es necesario contar con el siguiente software:

* **Oracle Database:** Versión Express Edition (XE) u otra versión compatible.
* **Oracle SQL Developer:** Herramienta gráfica para ejecutar los scripts PL/SQL.
* **MySQL Server:** Motor de base de datos para la migración final.
* **MySQL Workbench:** Interfaz gráfica para gestionar la base de datos MySQL.

---

## Pasos para Configurar el Entorno

Sigue estas indicaciones básicas para preparar tus conexiones antes de ejecutar el código:

1. Abre **Oracle SQL Developer**.
2. Crea una conexión nueva con tu usuario principal (por ejemplo, `SYSTEM`) y prueba la conexión.
3. Abre **MySQL Workbench**.
4. Crea una conexión local apuntando al puerto `3306` con tu usuario `root` y abre una nueva pestaña de consultas.

---

## Instrucciones para Ejecutar el Proyecto

Es fundamental ejecutar los archivos en el orden exacto que se indica a continuación para evitar errores con las claves foráneas y las dependencias. Las instrucciones están diseñadas paso a paso para que la ejecución sea sencilla y directa.

### Entorno Oracle (Diseño y PL/SQL)

Abre una hoja de trabajo en tu conexión de Oracle, asegúrate de tener la salida del servidor activada (`SET SERVEROUTPUT ON;`) y ejecuta los siguientes scripts uno por uno:

1. **Script DDL Oracle:** Ejecuta el código de creación de las 5 tablas principales (`Clientes`, `Usuarios`, `Productos`, `Ventas` y `Detalle Venta`).
2. **Script DML Inserción Oracle:** Ejecuta este bloque para insertar los 10 registros base en cada una de las tablas.
3. **Script Modificación y Eliminación:** Ejecuta este bloque anónimo que actualiza 5 registros y elimina otros 5 mediante operaciones seguras con control de excepciones.
4. **Script Procedimientos y Funciones:** Compila este archivo completo para crear en el sistema todos los objetos de lógica de negocio (10 funciones y 10 procedimientos).
5. **Script Cursores:** Ejecuta este bloque para visualizar las 25 consultas automatizadas a través de la consola de salida.
6. **Script Validaciones:** Ejecuta este bloque final para poner a prueba y confirmar en la consola el correcto funcionamiento de los procedimientos y funciones creados en el paso 4.

### Entorno MySQL (Migración para App Java)

Abre tu conexión en MySQL Workbench y ejecuta los siguientes scripts:

1. **Script DDL MySQL:** Ejecuta este archivo para crear la base de datos `powerbites` y la estructura de las tablas adaptada a la sintaxis de MySQL.
2. **Script DML MySQL:** Ejecuta este archivo final para poblar las tablas con los registros iniciales y dejar la base de datos lista para conectarse de forma persistente con la aplicación Java a través de JDBC.