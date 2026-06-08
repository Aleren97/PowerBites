package com.powerbites.controllers;

import com.powerbites.entities.Cliente;
import com.powerbites.services.ClienteService;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private final ClienteService clientService;
    private final Scanner scanner;
    public static final String RESET = "\u001B[0m";
    public static final String Verde = "\u001B[32m";

    public ClienteController() {
        this.clientService = new ClienteService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenuClients() {
        int option = -1;
        
        while (option != 0) {
            System.out.println(Verde+ "\n GESTIÓN DE CLIENTES \n" + RESET);
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Listar todos los clientes");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Exportar clientes a CSV");
            System.out.println("7. Mostrar mejor cliente");
            System.out.println("0. Volver al menú principal");
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
                case 6 -> export();
                case 7 -> clientService.obtenerMejorCliente();
                case 0 -> System.out.println("\nSaliendo del menu de clientes...");
                default -> System.out.println("\nOpción no valida.");
            }
        }
    }

    private void register() {
        System.out.println("\n REGISTRO DE CLIENTE \n");
        
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        
        System.out.print("Dirección: ");
        String adress = scanner.nextLine();

        Cliente newClient = new Cliente(0, name, email, phone, adress);
        clientService.registerClient(newClient);
    }

    private void search() {
        System.out.print("\nIntroduzca el ID del cliente a buscar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Cliente cliente = clientService.getById(id);
            
            if (cliente != null) {
                System.out.println(cliente);
            } else {
                System.out.println("\nNo se encontró ningún cliente con ese ID.");
            }
        
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void list() {
        System.out.println("\n LISTA DE CLIENTES \n");

        List<Cliente> clients = clientService.getAll();

        if (clients.isEmpty()) {
            System.out.println("\nNo hay clientes registrados en la base de datos.");
        } else {

            for (Cliente c : clients) {

                for (char letra : c.toString().toCharArray()) {
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
        System.out.print("\nIntroduzca el ID del cliente a modificar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Cliente currentClient = clientService.getById(id);

            if (currentClient != null) {
                System.out.println("\nDeje el campo en blanco y pulse 'Enter' si no desea modificarlo.");

                System.out.print("\nNuevo Nombre '(" + currentClient.getName() + ")': ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) currentClient.setName(name);

                System.out.print("Nuevo Email (" + currentClient.getEmail() + "): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) currentClient.setEmail(email);

                System.out.print("Nuevo Teléfono (" + currentClient.getPhone() + "): ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) currentClient.setPhone(phone);

                System.out.print("Nueva Dirección (" + currentClient.getAdress() + "): ");
                String adress = scanner.nextLine();
                if (!adress.isEmpty()) currentClient.setAdress(adress);

                clientService.modifyClient(currentClient);
            
            } else {
                System.out.println("\nNo se encontró el cliente.");
            }
        
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void delete() {
        System.out.print("\nIntroduzca el ID del cliente a eliminar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            clientService.deleteClient(id);
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void export() {
        System.out.println("\n EXPORTANDO DATOS \n");
        List<Cliente> clients = clientService.getAll();

        if (clients.isEmpty()) {
            System.out.println("\nNo hay clientes registrados en la base de datos para exportar.");
        } else {
            com.powerbites.util.GestorExportacion.exportClientsCSV(clients);
        }
    }
}