package com.powerbites.controllers;

import com.powerbites.entities.Venta;
import com.powerbites.services.VentaService;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class VentaController {

    private final VentaService ventaService;
    private final Scanner scanner;

    public VentaController() {
        this.ventaService = new VentaService();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuVentas() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("=== REGISTRO DE VENTAS ===");
            System.out.println("1. Registrar nueva venta");
            System.out.println("2. Buscar venta por ID");
            System.out.println("3. Listar todas las ventas");
            System.out.println("4. Modificar venta");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduzca un numero valido.");
                continue;
            }

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> buscar();
                case 3 -> listar();
                case 4 -> modificar();
                case 5 -> eliminar();
                case 0 -> System.out.println("Saliendo del modulo de ventas...");
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrar() {
        System.out.println("--- REGISTRO DE VENTA ---");
        try {
            System.out.print("ID del Cliente: ");
            int clienteId = Integer.parseInt(scanner.nextLine());

            System.out.print("ID del Comercial (Usuario): ");
            int usuarioId = Integer.parseInt(scanner.nextLine());

            System.out.print("Fecha (AAAA-MM-DD): ");
            Date fecha = Date.valueOf(scanner.nextLine());

            System.out.print("Estado (Ej: Completado, Pendiente, Cancelado): ");
            String estado = scanner.nextLine();

            System.out.print("Total de la venta: ");
            double total = Double.parseDouble(scanner.nextLine());

            Venta nuevaVenta = new Venta(0, clienteId, usuarioId, fecha, estado, total);
            ventaService.registrarVenta(nuevaVenta);

        } catch (IllegalArgumentException e) {
            System.out.println("Error en la entrada de datos. Compruebe los numeros y el formato de la fecha.");
        }
    }

    private void buscar() {
        System.out.print("Introduzca el ID de la venta a buscar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Venta venta = ventaService.buscarPorId(id);
            if (venta != null) {
                venta.mostrarDetalles();
            } else {
                System.out.println("No se encontro ninguna venta con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void listar() {
        System.out.println("--- HISTORIAL DE VENTAS ---");
        List<Venta> ventas = ventaService.obtenerTodas();
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas en el sistema.");
        } else {
            for (Venta v : ventas) {
                v.mostrarDetalles();
            }
        }
    }

    private void modificar() {
        System.out.print("Introduzca el ID de la venta a modificar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Venta ventaExistente = ventaService.buscarPorId(id);

            if (ventaExistente != null) {
                System.out.println("ATENCION: Para mantener la integridad, solo se permite modificar el estado y el total.");

                System.out.print("Nuevo Estado (" + ventaExistente.getEstado() + "): ");
                String estado = scanner.nextLine();
                if (!estado.isEmpty()) ventaExistente.setEstado(estado);

                System.out.print("Nuevo Total (" + ventaExistente.getTotal() + ") [Deje 0 para no modificar]: ");
                try {
                    double total = Double.parseDouble(scanner.nextLine());
                    if (total != 0) ventaExistente.setTotal(total);
                } catch (NumberFormatException e) {
                    System.out.println("Total no modificado (entrada no valida).");
                }

                ventaService.modificarVenta(ventaExistente);
            } else {
                System.out.println("No se encontro la venta.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void eliminar() {
        System.out.print("Introduzca el ID de la venta a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            ventaService.eliminarVenta(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }
}