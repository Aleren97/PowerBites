package com.powerbites.frontend;

import com.powerbites.controllers.*;
import com.powerbites.util.DataBaseConnection;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final ClienteController clientController;
    private final UsuarioController userController;
    private final ProductoController productController;
    private final VentaController saleController;
    private final DetalleVentaController detailSaleController;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.clientController = new ClienteController();
        this.userController = new UsuarioController();
        this.productController = new ProductoController();
        this.saleController = new VentaController();
        this.detailSaleController = new DetalleVentaController();
    }

    public void start() {
        int option = -1;

        while (option != 0) {
            System.out.println("\n POWERBITES CRM \n");
            System.out.println("1. Modulo de Clientes.");
            System.out.println("2. Modulo de Usuarios.");
            System.out.println("3. Modulo de Productos.");
            System.out.println("4. Modulo de Ventas.");
            System.out.println("5. Modulo de Detalles de Venta");
            System.out.println("0. Salir de la aplicacion.");
            System.out.print("\nSeleccione una opcion del menu principal: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nError: Por favor, introduzca un numero valido.");
                continue;
            }

            processOption(option);
        }
    }

    private void processOption(int option) {
        switch (option) {
            case 1 -> clientController.showMenuClients();
            case 2 -> userController.showMenuUser();
            case 3 -> productController.showMenuProducts();
            case 4 -> saleController.showMenuSales();
            case 5 -> detailSaleController.showMenuDetails();
            case 0 -> {
                System.out.println("\nCerrando el sistema CRM. Hasta pronto!");
                DataBaseConnection.closeConnection();
                scanner.close();
            }
            default -> System.out.println("\nOpcion no valida en el menu principal.");
        }
    }
}