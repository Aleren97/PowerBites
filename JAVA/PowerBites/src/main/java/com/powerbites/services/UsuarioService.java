package com.powerbites.services;

import com.powerbites.entities.Usuario;
import com.powerbites.repositories.UsuarioRepository;
import com.powerbites.repositories.UsuarioRepositoryImpl;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            System.out.println("Error: El nombre del usuario no puede estar vacio.");
            return;
        }
        usuarioRepository.crear(usuario);
    }

    public Usuario buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("Error: El ID introducido no es valido.");
            return null;
        }
        return usuarioRepository.leerPorId(id);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.leerTodos();
    }

    public void modificarUsuario(Usuario usuario) {
        if (usuarioRepository.leerPorId(usuario.getId()) == null) {
            System.out.println("Error: El usuario que intenta modificar no existe.");
            return;
        }
        usuarioRepository.actualizar(usuario);
    }

    public void eliminarUsuario(int id) {
        if (usuarioRepository.leerPorId(id) == null) {
            System.out.println("Error: El usuario que intenta eliminar no existe.");
            return;
        }
        usuarioRepository.eliminar(id);
    }
}