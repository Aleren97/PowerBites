package com.powerbites.services;

import com.powerbites.entities.Cliente;
import com.powerbites.repositories.ClienteRepository;
import com.powerbites.repositories.ClienteRepositoryImpl;
import java.util.List;

public class ClienteService {

    private final ClienteRepository clientRepository;

    public ClienteService() {
        this.clientRepository = new ClienteRepositoryImpl();
    }

    public void registerClient(Cliente client) {
        if (client.getName() == null || client.getName().isEmpty()) {
            System.out.println("\nError: El nombre del cliente no puede estar vacío.");
            return;
        }
        clientRepository.create(client);
    }

    public Cliente getById(int id) {
        if (id <= 0) {
            System.out.println("\nError: El ID introducido no es válido.");
            return null;
        }
        return clientRepository.readById(id);
    }

    public List<Cliente> getAll() {
        return clientRepository.readAll();
    }

    public void modifyClient(Cliente client) {
        if (clientRepository.readById(client.getId()) == null) {
            System.out.println("\nError: El cliente que intenta modificar no existe.");
            return;
        }
        clientRepository.refresh(client);
    }

    public void deleteClient(int id) {
        if (clientRepository.readById(id) == null) {
            System.out.println("\nError: El cliente que intenta eliminar no existe.");
            return;
        }
        clientRepository.delete(id);
    }
}