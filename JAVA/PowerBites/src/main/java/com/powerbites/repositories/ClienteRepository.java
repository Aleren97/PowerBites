package com.powerbites.repositories;

import com.powerbites.entities.Cliente;
import java.util.List;

public interface ClienteRepository {

    void create(Cliente client);

    Cliente readById(int id);

    List<Cliente> readAll();

    void update(Cliente client);

    void delete(int id);

    Cliente findMejorCliente();
}