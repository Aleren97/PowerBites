package com.powerbites.repositories;

import com.powerbites.entities.Usuario;
import java.util.List;

public interface UsuarioRepository {

    void create(Usuario user);

    Usuario readById(int id);

    List<Usuario> readAll();

    void update(Usuario user);

    void delete(int id);
}