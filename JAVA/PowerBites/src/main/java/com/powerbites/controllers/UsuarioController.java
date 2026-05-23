package com.powerbites.controllers;

import com.powerbites.entities.Usuario;
import com.powerbites.services.UsuarioService;
import java.util.List;
import java.util.Scanner;

public class UsuarioController {

    private final UsuarioService usuarioService;
    private final Scanner scanner;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuUsuarios() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("=== GESTION DE USUARIOS (COMERCIALES) ===");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Buscar usuario por ID");
            System.out.println("3. Listar todos los usuarios");
            System.out.println("4. Modificar usuario");
            System.out.println("5. Eliminar usuario");
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
                case 0: System.out.println("Saliendo del menu de usuarios..."); break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrar() {
        System.out.println("--- REGISTRO DE USUARIO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Rol (Ventas, Logistica, Administrador...): ");
        String rol = scanner.nextLine();
        System.out.print("Contrasena: ");
        String password = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario(0, nombre, email, rol, password);
        usuarioService.registrarUsuario(nuevoUsuario);
    }

    private void buscar() {
        System.out.print("Introduzca el ID del usuario a buscar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Usuario usuario = usuarioService.buscarPorId(id);
            if (usuario != null) {
                usuario.mostrarDetalles();
            } else {
                System.out.println("No se encontro ningun usuario con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void listar() {
        System.out.println("--- LISTA DE USUARIOS ---");
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : usuarios) {
                u.mostrarDetalles();
            }
        }
    }

    private void modificar() {
        System.out.print("Introduzca el ID del usuario a modificar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Usuario usuarioExistente = usuarioService.buscarPorId(id);

            if (usuarioExistente != null) {
                System.out.println("Deje el campo en blanco y pulse Enter si no desea modificarlo.");

                System.out.print("Nuevo Nombre (" + usuarioExistente.getNombre() + "): ");
                String nombre = scanner.nextLine();
                if (!nombre.isEmpty()) usuarioExistente.setNombre(nombre);

                System.out.print("Nuevo Email (" + usuarioExistente.getEmail() + "): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) usuarioExistente.setEmail(email);

                System.out.print("Nuevo Rol (" + usuarioExistente.getRol() + "): ");
                String rol = scanner.nextLine();
                if (!rol.isEmpty()) usuarioExistente.setRol(rol);

                System.out.print("Nueva Contrasena: ");
                String password = scanner.nextLine();
                if (!password.isEmpty()) usuarioExistente.setPasswordHash(password);

                usuarioService.modificarUsuario(usuarioExistente);
            } else {
                System.out.println("No se encontro el usuario.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }

    private void eliminar() {
        System.out.print("Introduzca el ID del usuario a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            usuarioService.eliminarUsuario(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
        }
    }
}