package com.powerbites.controllers;

import com.powerbites.entities.DetalleVenta;
import com.powerbites.services.DetalleVentaService;
import java.util.List;
import java.util.Scanner;

public class DetalleVentaController {

    private final DetalleVentaService detalleService;
    private final Scanner scanner;

    public DetalleVentaController() {
        this.detalleService = new DetalleVentaService();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuDetalles() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("=== GESTION DE DETALLES DE VENTA ===");
            System.out.println("1. Registrar nueva linea de detalle");
            System.out.println("2. Buscar detalle por ID");
            System.out.println("3. Listar todos los detalles");
            System.out.println("4. Modificar detalle");
            System.out.println("5. Eliminar detalle");
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
                case 0: System.out.println("Saliendo del modulo de detalles..."); break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrar() {
        System.out.println("--- REGISTRO DE LINEA DE DETALLE ---");
        try {
            System.out.print("ID de la Venta asociada: ");
            int ventaId = Integer.parseInt(scanner.nextLine());

            System.out.print("ID del Producto: ");
            int productoId = Integer.parseInt(scanner.nextLine());

            System.out.print("Cantidad vendida: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            System.out.print("Precio Unitario: ");
            double precio = Double.parseDouble(scanner.nextLine());

            DetalleVenta nuevoDetalle = new DetalleVenta(0, ventaId, productoId, cantidad, precio);
            detalleService.registrarDetalle(nuevoDetalle);

        } catch (NumberFormatException e) {
            System.out.println("Error en la entrada de datos. Asegurese de introducir solo numeros.");
        }
    }

    private void buscar() {
        System.out.print("Introduzca el ID del detalle a buscar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            DetalleVenta detalle = detalleService.buscarPorId(id);
            if (detalle != null) {
                detalle.mostrarDetalles();
            } else {
                System.out.println("No se encontro ningun detalle con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void listar() {
        System.out.println("--- HISTORIAL DE DETALLES ---");
        List<DetalleVenta> detalles = detalleService.obtenerTodos();
        if (detalles.isEmpty()) {
            System.out.println("No hay lineas de detalle registradas.");
        } else {
            for (DetalleVenta d : detalles) {
                d.mostrarDetalles();
            }
        }
    }

    private void modificar() {
        System.out.print("Introduzca el ID del detalle a modificar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            DetalleVenta detalleExistente = detalleService.buscarPorId(id);

            if (detalleExistente != null) {
                System.out.print("Nueva Cantidad (" + detalleExistente.getCantidad() + ") [Deje 0 para no modificar]: ");
                try {
                    int cantidad = Integer.parseInt(scanner.nextLine());
                    if (cantidad > 0) detalleExistente.setCantidad(cantidad);
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad no modificada.");
                }

                System.out.print("Nuevo Precio Unitario (" + detalleExistente.getPrecioUnitario() + ") [Deje 0 para no modificar]: ");
                try {
                    double precio = Double.parseDouble(scanner.nextLine());
                    if (precio > 0) detalleExistente.setPrecioUnitario(precio);
                } catch (NumberFormatException e) {
                    System.out.println("Precio no modificado.");
                }

                detalleService.modificarDetalle(detalleExistente);
            } else {
                System.out.println("No se encontro el detalle.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void eliminar() {
        System.out.print("Introduzca el ID del detalle a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            detalleService.eliminarDetalle(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }
}