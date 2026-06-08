package com.powerbites.services;

import com.powerbites.entities.DetalleVenta;
import com.powerbites.repositories.DetalleVentaRepository;
import com.powerbites.repositories.DetalleVentaRepositoryImpl;
import java.util.List;

public class DetalleVentaService {

    private final DetalleVentaRepository detailRepository;

    public DetalleVentaService() {
        this.detailRepository = new DetalleVentaRepositoryImpl();
    }

    public void registerDetail(DetalleVenta detail) {
        if (detail.getAmount() <= 0) {
            System.out.println("\nError: La cantidad debe ser mayor que cero.");
            return;
        }
        if (detail.getCurrentPrice() < 0) {
            System.out.println("\nError: El precio unitario no puede ser negativo.");
            return;
        }
        detailRepository.create(detail);
    }

    public DetalleVenta getById(int id) {
        if (id <= 0) {
            System.out.println("\nError: El ID introducido no es valido.");
            return null;
        }
        return detailRepository.readById(id);
    }

    public List<DetalleVenta> getAll() {
        return detailRepository.readAll();
    }

    public void modifyDetail(DetalleVenta detail) {
        if (detailRepository.readById(detail.getId()) == null) {
            System.out.println("\nError: La linea de detalle que intenta modificar no existe.");
            return;
        }
        detailRepository.update(detail);
    }

    public void deleteDetail(int id) {
        if (detailRepository.readById(id) == null) {
            System.out.println("\nError: La linea de detalle que intenta eliminar no existe.");
            return;
        }
        detailRepository.delete(id);
    }
}