package com.powerbites.controllers;

import com.powerbites.entities.Cliente;
import com.powerbites.services.ClienteService;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private final ClienteService clienteService;
    private final Scanner scanner;

    public ClienteController() {
        this.clienteService = new ClienteService();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuClientes() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("=== GESTION DE CLIENTES ===");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Listar todos los clientes");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Exportar clientes a CSV");
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
                case 6 -> exportar();
                case 0 -> System.out.println("Saliendo del menu de clientes...");
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrar() {
        System.out.println("--- REGISTRO DE CLIENTE ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();
        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();

        Cliente nuevoCliente = new Cliente(0, nombre, email, telefono, direccion);
        clienteService.registrarCliente(nuevoCliente);
    }

    private void buscar() {
        System.out.print("Introduzca el ID del cliente a buscar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Cliente cliente = clienteService.buscarPorId(id);
            if (cliente != null) {
                cliente.mostrarDetalles();
            } else {
                System.out.println("No se encontro ningun cliente con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void listar() {
        System.out.println("--- LISTA DE CLIENTES ---");
        List<Cliente> clientes = clienteService.obtenerTodos();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados en la base de datos.");
        } else {
            for (Cliente c : clientes) {
                c.mostrarDetalles();
            }
        }
    }

    private void modificar() {
        System.out.print("Introduzca el ID del cliente a modificar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Cliente clienteExistente = clienteService.buscarPorId(id);

            if (clienteExistente != null) {
                System.out.println("Deje el campo en blanco y pulse Enter si no desea modificarlo.");

                System.out.print("Nuevo Nombre (" + clienteExistente.getNombre() + "): ");
                String nombre = scanner.nextLine();
                if (!nombre.isEmpty()) clienteExistente.setNombre(nombre);

                System.out.print("Nuevo Email (" + clienteExistente.getEmail() + "): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) clienteExistente.setEmail(email);

                System.out.print("Nuevo Telefono (" + clienteExistente.getTelefono() + "): ");
                String telefono = scanner.nextLine();
                if (!telefono.isEmpty()) clienteExistente.setTelefono(telefono);

                System.out.print("Nueva Direccion (" + clienteExistente.getDireccion() + "): ");
                String direccion = scanner.nextLine();
                if (!direccion.isEmpty()) clienteExistente.setDireccion(direccion);

                clienteService.modificarCliente(clienteExistente);
            } else {
                System.out.println("No se encontro el cliente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void eliminar() {
        System.out.print("Introduzca el ID del cliente a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            clienteService.darDeBajaCliente(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void exportar() {
        System.out.println("--- EXPORTANDO DATOS ---");
        List<Cliente> clientes = clienteService.obtenerTodos();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados en la base de datos para exportar.");
        } else {
            com.powerbites.util.GestorExportacion.exportarClientesCSV(clientes);
        }
    }
}