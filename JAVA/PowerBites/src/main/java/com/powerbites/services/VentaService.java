package com.powerbites.services;

import com.powerbites.entities.Venta;
import com.powerbites.repositories.VentaRepository;
import com.powerbites.repositories.VentaRepositoryImpl;
import java.util.List;

public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService() {
        this.ventaRepository = new VentaRepositoryImpl();
    }

    public void registrarVenta(Venta venta) {
        if (venta.getTotal() < 0) {
            System.out.println("Error: El total de la venta no puede ser negativo.");
            return;
        }
        ventaRepository.crear(venta);
    }

    public Venta buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("Error: El ID introducido no es valido.");
            return null;
        }
        return ventaRepository.leerPorId(id);
    }

    public List<Venta> obtenerTodas() {
        return ventaRepository.leerTodos();
    }

    public void modificarVenta(Venta venta) {
        if (ventaRepository.leerPorId(venta.getId()) == null) {
            System.out.println("Error: La venta que intenta modificar no existe.");
            return;
        }
        ventaRepository.actualizar(venta);
    }

    public void eliminarVenta(int id) {
        if (ventaRepository.leerPorId(id) == null) {
            System.out.println("Error: La venta que intenta eliminar no existe.");
            return;
        }
        ventaRepository.eliminar(id);
    }
}