package com.powerbites.services;

import com.powerbites.entities.Cliente;
import com.powerbites.repositories.ClienteRepository;
import com.powerbites.repositories.ClienteRepositoryImpl;
import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }

    public void registrarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            System.out.println("Error: El nombre del cliente no puede estar vacío.");
            return;
        }
        clienteRepository.crear(cliente);
    }

    public Cliente buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("Error: El ID introducido no es válido.");
            return null;
        }
        return clienteRepository.leerPorId(id);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.leerTodos();
    }

    public void modificarCliente(Cliente cliente) {
        if (clienteRepository.leerPorId(cliente.getId()) == null) {
            System.out.println("Error: El cliente que intenta modificar no existe.");
            return;
        }
        clienteRepository.actualizar(cliente);
    }

    public void darDeBajaCliente(int id) {
        if (clienteRepository.leerPorId(id) == null) {
            System.out.println("Error: El cliente que intenta eliminar no existe.");
            return;
        }
        clienteRepository.eliminar(id);
    }
}