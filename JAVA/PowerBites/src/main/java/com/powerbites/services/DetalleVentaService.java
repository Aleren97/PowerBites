package com.powerbites.services;

import com.powerbites.entities.DetalleVenta;
import com.powerbites.repositories.DetalleVentaRepository;
import com.powerbites.repositories.DetalleVentaRepositoryImpl;
import java.util.List;

public class DetalleVentaService {

    private final DetalleVentaRepository detalleRepository;

    public DetalleVentaService() {
        this.detalleRepository = new DetalleVentaRepositoryImpl();
    }

    public void registrarDetalle(DetalleVenta detalle) {
        if (detalle.getCantidad() <= 0) {
            System.out.println("Error: La cantidad debe ser mayor que cero.");
            return;
        }
        if (detalle.getPrecioUnitario() < 0) {
            System.out.println("Error: El precio unitario no puede ser negativo.");
            return;
        }
        detalleRepository.crear(detalle);
    }

    public DetalleVenta buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("Error: El ID introducido no es valido.");
            return null;
        }
        return detalleRepository.leerPorId(id);
    }

    public List<DetalleVenta> obtenerTodos() {
        return detalleRepository.leerTodos();
    }

    public void modificarDetalle(DetalleVenta detalle) {
        if (detalleRepository.leerPorId(detalle.getId()) == null) {
            System.out.println("Error: La linea de detalle que intenta modificar no existe.");
            return;
        }
        detalleRepository.actualizar(detalle);
    }

    public void eliminarDetalle(int id) {
        if (detalleRepository.leerPorId(id) == null) {
            System.out.println("Error: La linea de detalle que intenta eliminar no existe.");
            return;
        }
        detalleRepository.eliminar(id);
    }
}