package com.powerbites.controllers;

import com.powerbites.entities.Venta;
import com.powerbites.services.VentaService;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class VentaController {

    private final VentaService saleService;
    private final Scanner scanner;

    public static final String RESET = "\u001B[0m";
    public static final String Rojo_Negrita = "\u001B[1;31m";

    public VentaController() {
        this.saleService = new VentaService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenuSales() {
        int option = -1;
        while (option != 0) {
            System.out.println(Rojo_Negrita+ "\n REGISTRO DE VENTAS \n" +RESET);
            System.out.println("1. Registrar nueva venta");
            System.out.println("2. Buscar venta por ID");
            System.out.println("3. Listar todas las ventas");
            System.out.println("4. Modificar venta");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nError: Por favor, introduzca un numero valido.");
                continue;
            }

            switch (option) {
                case 1 -> register();
                case 2 -> search();
                case 3 -> list();
                case 4 -> modify();
                case 5 -> delete();
                case 0 -> System.out.println("\nSaliendo del modulo de ventas...");
                default -> System.out.println("\nOpción no valida.");
            }
        }
    }

    private void register() {
        System.out.println("\n REGISTRO DE VENTA \n");
        try {
            System.out.print("ID del Cliente: ");
            int clientId = Integer.parseInt(scanner.nextLine());

            System.out.print("ID del Comercial (Usuario): ");
            int userId = Integer.parseInt(scanner.nextLine());

            System.out.print("Fecha (AAAA-MM-DD): ");
            Date date = Date.valueOf(scanner.nextLine());

            System.out.print("Estado (Ej: Completado, Pendiente, Cancelado): ");
            String status = scanner.nextLine();

            System.out.print("Total de la venta: ");
            double total = Double.parseDouble(scanner.nextLine());

            Venta newSale = new Venta(0, clientId, userId, date, status, total);
            saleService.registerSale(newSale);

        } catch (IllegalArgumentException e) {
            System.out.println("\nError en la entrada de datos. Compruebe los números y el formato de la fecha.");
        }
    }

    private void search() {
        System.out.print("\nIntroduzca el ID de la venta a buscar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Venta sale = saleService.getById(id);
            
            if (sale != null) {
                System.out.println(sale);
            } else {
                System.out.println("\nNo se encontró ninguna venta con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void list() {
        System.out.println("\n HISTORIAL DE VENTAS \n");
        List<Venta> sales = saleService.getAll();
        
        if (sales.isEmpty()) {
            System.out.println("\nNo hay ventas registradas en el sistema.");
        } else {
            for (Venta v : sales) {
                for (char letra : v.toString().toCharArray()) {
                    System.out.print(letra);

                    try { Thread.sleep(15);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
            }
        }
    }

    private void modify() {
        System.out.print("\nIntroduzca el ID de la venta a modificar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Venta currentSale = saleService.getById(id);

            if (currentSale != null) {
                System.out.println("\nATENCIÓN: Para mantener la integridad, solo se permite modificar el estado y el total.");

                System.out.print("\nNuevo Estado (" + currentSale.getStatus() + "): ");
                String status = scanner.nextLine();
                
                if (!status.isEmpty()) currentSale.setStatus(status);

                System.out.print("Nuevo Total (" + currentSale.getTotal() + ") [Deje 0 para no modificar]: ");
                
                try {
                    double total = Double.parseDouble(scanner.nextLine());
                    if (total != 0) currentSale.setTotal(total);
                } catch (NumberFormatException e) {
                    System.out.println("\nTotal no modificado (entrada no valida).");
                }

                saleService.modifySale(currentSale);
            } else {
                System.out.println("\nNo se encontró la venta.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void delete() {
        System.out.print("\nIntroduzca el ID de la venta a eliminar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            saleService.deleteSale(id);
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }
}