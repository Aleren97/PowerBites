package com.powerbites.controllers;

import com.powerbites.entities.Producto;
import com.powerbites.services.ProductoService;
import java.util.List;
import java.util.Scanner;

public class ProductoController {

    private final ProductoService productoService;
    private final Scanner scanner;

    public ProductoController() {
        this.productoService = new ProductoService();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuProductos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("=== CATALOGO DE PRODUCTOS ===");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Listar todos los productos");
            System.out.println("4. Modificar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduzca un numero valido.");
                continue;
            }

            switch (opcion) {
                case 1: registrar(); break;
                case 2: buscar(); break;
                case 3: listar(); break;
                case 4: modificar(); break;
                case 5: eliminar(); break;
                case 0: System.out.println("Saliendo del catalogo de productos..."); break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrar() {
        System.out.println("--- REGISTRO DE PRODUCTO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        double precio = 0.0;
        boolean precioValido = false;
        while (!precioValido) {
            System.out.print("Precio: ");
            try {
                precio = Double.parseDouble(scanner.nextLine());
                precioValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduzca un precio valido (ej: 2.50).");
            }
        }

        System.out.print("Categoria (Ej: Proteina, Vegana...): ");
        String categoria = scanner.nextLine();

        Producto nuevoProducto = new Producto(0, nombre, descripcion, precio, categoria);
        productoService.registrarProducto(nuevoProducto);
    }

    private void buscar() {
        System.out.print("Introduzca el ID del producto a buscar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Producto producto = productoService.buscarPorId(id);
            if (producto != null) {
                producto.mostrarDetalles();
            } else {
                System.out.println("No se encontro ningun producto con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void listar() {
        System.out.println("--- LISTA DE PRODUCTOS ---");
        List<Producto> productos = productoService.obtenerTodos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados en el catalogo.");
        } else {
            for (Producto p : productos) {
                p.mostrarDetalles();
            }
        }
    }

    private void modificar() {
        System.out.print("Introduzca el ID del producto a modificar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Producto productoExistente = productoService.buscarPorId(id);

            if (productoExistente != null) {
                System.out.println("Deje el campo en blanco y pulse Enter si no desea modificarlo.");

                System.out.print("Nuevo Nombre (" + productoExistente.getNombre() + "): ");
                String nombre = scanner.nextLine();
                if (!nombre.isEmpty()) productoExistente.setNombre(nombre);

                System.out.print("Nueva Descripcion (" + productoExistente.getDescripcion() + "): ");
                String descripcion = scanner.nextLine();
                if (!descripcion.isEmpty()) productoExistente.setDescripcion(descripcion);

                System.out.print("Nuevo Precio (" + productoExistente.getPrecio() + ") [Deje 0 para no modificar]: ");
                try {
                    double precio = Double.parseDouble(scanner.nextLine());
                    if (precio != 0) productoExistente.setPrecio(precio);
                } catch (NumberFormatException e) {
                    System.out.println("Precio no modificado (entrada no valida).");
                }

                System.out.print("Nueva Categoria (" + productoExistente.getCategoria() + "): ");
                String categoria = scanner.nextLine();
                if (!categoria.isEmpty()) productoExistente.setCategoria(categoria);

                productoService.modificarProducto(productoExistente);
            } else {
                System.out.println("No se encontro el producto.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void eliminar() {
        System.out.print("Introduzca el ID del producto a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            productoService.eliminarProducto(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }
}