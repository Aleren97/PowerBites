# PowerBites CRM - Backend 

Sistema de gestión de relaciones con clientes (CRM) desarrollado en Java puro para la administración interna de la empresa **PowerBites**. Este proyecto corresponde al módulo de Programación y Entornos de Desarrollo del segundo trimestre del ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM).

## Tecnologías y Herramientas

* **Lenguaje:** Java 21
* **Base de Datos:** MySQL 8.0.43 (Community Server)
* **Gestor de Dependencias:** Maven
* **Driver JDBC:** `mysql-connector-j`
* **IDE Recomendado:** IntelliJ IDEA (Ultimate/Community Edition)

## Arquitectura del Software

El proyecto sigue una estricta **Arquitectura en Capas** para garantizar un código limpio, escalable y mantenible, aplicando los principios de la Programación Orientada a Objetos:

* **`entities` (Modelo):** Contiene las clases planas (POJOs) que representan las tablas de la base de datos aplicando herencia (`Persona` -> `Cliente`, `Usuario`).
* **`repositories` (DAO):** Interfaces y sus implementaciones que gestionan la persistencia de datos (CRUD) ejecutando sentencias SQL preparadas (`PreparedStatement`) para evitar inyecciones SQL.
* **`services` (Lógica de Negocio):** Capa intermediaria que aplica validaciones y reglas de negocio antes de interactuar con la base de datos.
* **`controllers` (Controladores):** Gestionan la interacción con el usuario, recogen los datos por consola y los envían a los servicios correspondientes.
* **`frontend` (Vistas):** Interfaz de línea de comandos (CLI) principal y menús de navegación del sistema.
* **`util` (Utilidades):** Clases transversales, incluyendo el patrón Singleton para la conexión a la base de datos (`DataBaseConnection`) y la funcionalidad de exportación a ficheros (`GestorExportacion`).

## Funcionalidades Principales

El CRM está dividido en 5 módulos principales, accesibles a través de un menú interactivo en consola:

1.  **Módulo de Clientes:** Altas, bajas, modificaciones, consultas y exportación de toda la cartera de clientes a formato `.csv`.
2.  **Módulo de Usuarios:** Gestión de los trabajadores (comerciales, administradores) que utilizan el sistema.
3.  **Módulo de Productos:** Catálogo de artículos con control de categorías, descripciones y precios.
4.  **Módulo de Ventas:** Registro de cabeceras de ventas vinculando Clientes y Usuarios, junto con su estado y total.
5.  **Módulo de Detalles de Venta:** Gestión de las líneas individuales de cada venta (productos y cantidades).

## Instalación y Despliegue

### 1. Preparar la Base de Datos
Para ejecutar el proyecto, es necesario tener un servidor MySQL en funcionamiento.
1. Crea una base de datos llamada `powerbites`.
2. Ejecuta los scripts SQL correspondientes (ubicados en los *tags* anteriores del repositorio) para crear la estructura de tablas.

### 2. Configurar Credenciales
Por defecto, el sistema intentará conectarse a la base de datos local utilizando las siguientes credenciales configuradas en `src/main/java/com/powerbites/util/DataBaseConnection.java`:
* **URL:** `jdbc:mysql://localhost:3306/powerbites`
* **Usuario:** `root`
* **Contraseña:** `1234`
*(Modifica estos valores en el código si tu configuración local de MySQL es diferente).*

### 3. Ejecución
1. Clona este repositorio en tu máquina local.
2. Abre la carpeta del proyecto en tu IDE (IntelliJ, Eclipse, etc.). Maven resolverá y descargará automáticamente el driver de MySQL especificado en el `pom.xml`.
3. Ejecuta la clase `Main.java` ubicada en `src/main/java/com/powerbites/Main.java`.