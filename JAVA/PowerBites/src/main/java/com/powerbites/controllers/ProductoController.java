package com.powerbites.controllers;

import com.powerbites.entities.Producto;
import com.powerbites.services.ProductoService;
import java.util.List;
import java.util.Scanner;

public class ProductoController {

    private final ProductoService productService;
    private final Scanner scanner;
    public static final String RESET = "\u001B[0m";
    public static final String Amarillo = "\u001B[33m";

    public ProductoController() {
        this.productService = new ProductoService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenuProducts() {
        int option = -1;
        while (option != 0) {
            System.out.println(Amarillo + "\n CATALOGO DE PRODUCTOS \n" + RESET);
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Listar todos los productos");
            System.out.println("4. Modificar producto");
            System.out.println("5. Eliminar producto");
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
                case 0 -> System.out.println("\nSaliendo del catalogo de productos...");
                default -> System.out.println("\nOpcion no valida.");
            }
        }
    }

    private void register() {
        System.out.println("\n REGISTRO DE PRODUCTO \n");
        
        System.out.print("\nNombre: ");
        String name = scanner.nextLine();
        
        System.out.print("Descripcion: ");
        String description = scanner.nextLine();

        double price = 0.0;
        boolean Validprice = false;
        while (!Validprice) {
            System.out.print("Precio: ");
            
            try {
                price = Double.parseDouble(scanner.nextLine());
                Validprice = true;
            } catch (NumberFormatException e) {
                System.out.println("\nError: Introduzca un precio valido (ej: 2.50).");
            }
        }

        System.out.print("\nCategoria (Ej: Proteina, Vegana...): ");
        String category = scanner.nextLine();

        Producto newProduct = new Producto(0, name, description, price, category);
        productService.registerProduct(newProduct);
    }

    private void search() {
        System.out.print("\nIntroduzca el ID del producto a buscar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Producto product = productService.getById(id);
            
            if (product != null) {
                System.out.println(product);
            } else {
                System.out.println("\nNo se encontro ningun producto con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void list() {
        System.out.println("\n LISTA DE PRODUCTOS \n");
        List<Producto> products = productService.getAll();
        if (products.isEmpty()) {
            System.out.println("\nNo hay productos registrados en el catalogo.");
        } else {
            for (Producto p : products) {

                for (char letra : p.toString().toCharArray()) {
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
        System.out.print("\nIntroduzca el ID del producto a modificar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Producto currentProduct = productService.getById(id);

            if (currentProduct != null) {
                System.out.println("\nDeje el campo en blanco y pulse Enter si no desea modificarlo.");

                System.out.print("\nNuevo Nombre (" + currentProduct.getName() + "): ");
                String name = scanner.nextLine();
                
                if (!name.isEmpty()) currentProduct.setName(name);

                System.out.print("Nueva Descripcion (" + currentProduct.getDescription() + "): ");
                String description = scanner.nextLine();
                
                if (!description.isEmpty()) currentProduct.setDescription(description);

                System.out.print("Nuevo Precio (" + currentProduct.getPrice() + ") [Deje 0 para no modificar]: ");
                
                try {
                    double price = Double.parseDouble(scanner.nextLine());
                    if (price != 0) currentProduct.setPrice(price);
                } catch (NumberFormatException e) {
                    System.out.println("\nPrecio no modificado (entrada no valida).");
                }

                System.out.print("\nNueva Categoria (" + currentProduct.getCategory() + "): ");
                String category = scanner.nextLine();
                if (!category.isEmpty()) currentProduct.setCategory(category);

                productService.modifyProduct(currentProduct);
            } else {
                System.out.println("\nNo se encontro el producto.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void delete() {
        System.out.print("Introduzca el ID del producto a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            productService.deleteProduct(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }
}