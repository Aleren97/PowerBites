package com.powerbites.frontend;

import com.powerbites.controllers.*;
import com.powerbites.util.DataBaseConnection;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final ClienteController clienteController;
    private final UsuarioController usuarioController;
    private final ProductoController productoController;
    private final VentaController ventaController;
    private final DetalleVentaController detalleVentaController;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.clienteController = new ClienteController();
        this.usuarioController = new UsuarioController();
        this.productoController = new ProductoController();
        this.ventaController = new VentaController();
        this.detalleVentaController = new DetalleVentaController();
    }

    public void iniciar() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("=== POWERBITES CRM ===");
            System.out.println("1. Modulo de Clientes.");
            System.out.println("2. Modulo de Usuarios.");
            System.out.println("3. Modulo de Productos.");
            System.out.println("4. Modulo de Ventas.");
            System.out.println("5. Modulo de Detalles de Venta");
            System.out.println("0. Salir de la aplicacion.");
            System.out.print("Seleccione una opcion del menu principal: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduzca un numero valido.");
                continue;
            }

            procesarOpcion(opcion);
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> clienteController.mostrarMenuClientes();
            case 2 -> usuarioController.mostrarMenuUsuarios();
            case 3 -> productoController.mostrarMenuProductos();
            case 4 -> ventaController.mostrarMenuVentas();
            case 5 -> detalleVentaController.mostrarMenuDetalles();
            case 0 -> {
                System.out.println("Cerrando el sistema CRM. Hasta pronto!");
                DataBaseConnection.closeConnection();
                scanner.close();
            }
            default -> System.out.println("Opcion no valida en el menu principal.");
        }
    }
}