package com.powerbites.repositories;

import com.powerbites.entities.Usuario;
import java.util.List;

public interface UsuarioRepository {

    void crear(Usuario usuario);

    Usuario leerPorId(int id);

    List<Usuario> leerTodos();

    void actualizar(Usuario usuario);

    void eliminar(int id);
}