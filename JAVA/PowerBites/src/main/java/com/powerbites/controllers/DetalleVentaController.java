package com.powerbites.controllers;

import com.powerbites.entities.DetalleVenta;
import com.powerbites.services.DetalleVentaService;
import java.util.List;
import java.util.Scanner;

public class DetalleVentaController {

    private final DetalleVentaService detailService;
    private final Scanner scanner;

    public DetalleVentaController() {
        this.detailService = new DetalleVentaService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenuDetails() {
        int option = -1;
        while (option != 0) {
            System.out.println("\n GESTION DE DETALLES DE VENTA \n");
            System.out.println("1. Registrar nueva linea de detalle");
            System.out.println("2. Buscar detalle por ID");
            System.out.println("3. Listar todos los detalles");
            System.out.println("4. Modificar detalle");
            System.out.println("5. Eliminar detalle");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

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
                case 0 -> System.out.println("\nSaliendo del modulo de detalles...");
                default -> System.out.println("\nOpcion no valida.");
            }
        }
    }

    private void register() {
        System.out.println("\n REGISTRO DE LINEA DE DETALLE \n");
        
        try {
            System.out.print("ID de la Venta asociada: ");
            int saleId = Integer.parseInt(scanner.nextLine());

            System.out.print("ID del Producto: ");
            int productId = Integer.parseInt(scanner.nextLine());

            System.out.print("Cantidad vendida: ");
            int amount = Integer.parseInt(scanner.nextLine());

            System.out.print("Precio Unitario: ");
            double price = Double.parseDouble(scanner.nextLine());

            DetalleVenta newDetail = new DetalleVenta(0, saleId, productId, amount, price);
            detailService.registerDetail(newDetail);

        } catch (NumberFormatException e) {
            System.out.println("Error en la entrada de datos. Asegurese de introducir solo numeros.");
        }
    }

    private void search() {
        System.out.print("\nIntroduzca el ID del detalle a buscar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            DetalleVenta detail = detailService.getById(id);
            
            if (detail != null) {
                System.out.println(detail);
            } else {
                System.out.println("\nNo se encontro ningun detalle con ese ID.");
            }
        
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void list() {
        System.out.println("\n HISTORIAL DE DETALLES \n");
        List<DetalleVenta> details = detailService.getAll();
        
        if (details.isEmpty()) {
            System.out.println("\nNo hay lineas de detalle registradas.");
        } else {
            for (DetalleVenta d : details) {

                for (char letra : d.toString().toCharArray()) {
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
        System.out.print("\nIntroduzca el ID del detalle a modificar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            DetalleVenta currentDetail = detailService.getById(id);

            if (currentDetail != null) {
                System.out.print("\nNueva Cantidad (" + currentDetail.getAmount() + ") [Deje 0 para no modificar]: ");
                
                try {
                    int amount = Integer.parseInt(scanner.nextLine());
                    
                    if (amount > 0) currentDetail.setAmount(amount);
                
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad no modificada.");
                }

                System.out.print("\nNuevo Precio Unitario (" + currentDetail.getCurrentPrice() + ") [Deje 0 para no modificar]: ");
                
                try {
                    double price = Double.parseDouble(scanner.nextLine());
                    
                    if (price > 0) currentDetail.setCurrentPrice(price);
                
                } catch (NumberFormatException e) {
                    System.out.println("\nPrecio no modificado.");
                }

                detailService.modifyDetail(currentDetail);
            } else {
                System.out.println("\nNo se encontro el detalle.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void delete() {
        System.out.print("\nIntroduzca el ID del detalle a eliminar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            detailService.deleteDetail(id);
        
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }
}