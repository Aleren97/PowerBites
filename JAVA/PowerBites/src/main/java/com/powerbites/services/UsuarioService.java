package com.powerbites.services;

import com.powerbites.entities.Usuario;
import com.powerbites.repositories.UsuarioRepository;
import com.powerbites.repositories.UsuarioRepositoryImpl;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository userRepository;

    public UsuarioService() {
        this.userRepository = new UsuarioRepositoryImpl();
    }

    public void registerUser(Usuario user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            System.out.println("\nError: El nombre del usuario no puede estar vacio.");
            return;
        }
        userRepository.create(user);
    }

    public Usuario getById(int id) {
        if (id <= 0) {
            System.out.println("\nError: El ID introducido no es valido.");
            return null;
        }
        return userRepository.readById(id);
    }

    public List<Usuario> getAll() {
        return userRepository.readAll();
    }

    public void modifyUser(Usuario user) {
        if (userRepository.readById(user.getId()) == null) {
            System.out.println("\nError: El usuario que intenta modificar no existe.");
            return;
        }
        userRepository.update(user);
    }

    public void deleteUser(int id) {
        if (userRepository.readById(id) == null) {
            System.out.println("\nError: El usuario que intenta eliminar no existe.");
            return;
        }
        userRepository.delete(id);
    }
}