package com.powerbites.controllers;

import com.powerbites.entities.Usuario;
import com.powerbites.services.UsuarioService;
import java.util.List;
import java.util.Scanner;

public class UsuarioController {

    private final UsuarioService userService;
    private final Scanner scanner;
    public static final String RESET = "\u001B[0m";
    public static final String Azul = "\u001B[34m";

    public UsuarioController() {
        this.userService = new UsuarioService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenuUser() {
        int option = -1;
        while (option != 0) {
            System.out.println(Azul + "\n GESTION DE USUARIOS (COMERCIALES) \n" + RESET);
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("3. Listar todos los usuarios");
            System.out.println("4. Modificar usuario");
            System.out.println("5. Eliminar usuario");
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
                case 0 -> System.out.println("\nSaliendo del menu de usuarios...");
                default -> System.out.println("\nOpcion no valida.");
            }
        }
    }

    private void register() {
        System.out.println("\n REGISTRO DE USUARIO \n");
        
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Rol (Ventas, Logistica, Administrador...): ");
        String rol = scanner.nextLine();
        
        System.out.print("Contrasena: ");
        String password = scanner.nextLine();

        Usuario newUser = new Usuario(0, name, email, rol, password);
        userService.registerUser(newUser);
    }

    private void search() {
        System.out.print("\nIntroduzca el ID del usuario a buscar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Usuario user = userService.getById(id);
            
            if (user != null) {
                System.out.println(user);
            } else {
                System.out.println("\nNo se encontro ningun usuario con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void list() {
        System.out.println("\n LISTA DE USUARIOS \n");
        List<Usuario> users = userService.getAll();
        
        if (users.isEmpty()) {
            System.out.println("\nNo hay usuarios registrados.");
        } else {
            for (Usuario u : users) {

                for (char letra : u.toString().toCharArray()) {
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
        System.out.print("\nIntroduzca el ID del usuario a modificar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Usuario currentUser = userService.getById(id);

            if (currentUser != null) {
                System.out.println("\nDeje el campo en blanco y pulse Enter si no desea modificarlo.");

                System.out.print("\nNuevo Nombre (" + currentUser.getName() + "): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) currentUser.setName(name);

                System.out.print("Nuevo Email (" + currentUser.getEmail() + "): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) currentUser.setEmail(email);

                System.out.print("Nuevo Rol (" + currentUser.getRol() + "): ");
                String rol = scanner.nextLine();
                if (!rol.isEmpty()) currentUser.setRol(rol);

                System.out.print("Nueva Contrasena: ");
                String password = scanner.nextLine();
                if (!password.isEmpty()) currentUser.setPasswordHash(password);

                userService.modifyUser(currentUser);
            } else {
                System.out.println("\nNo se encontro el usuario.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }

    private void delete() {
        System.out.print("\nIntroduzca el ID del usuario a eliminar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            userService.deleteUser(id);
        } catch (NumberFormatException e) {
            System.out.println("\nID invalido.");
        }
    }
}