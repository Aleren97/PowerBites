package com.powerbites.repositories;

import com.powerbites.entities.Cliente;
import java.util.List;

public interface ClienteRepository {

    void crear(Cliente cliente);

    Cliente leerPorId(int id);

    List<Cliente> leerTodos();

    void actualizar(Cliente cliente);

    void eliminar(int id);
}