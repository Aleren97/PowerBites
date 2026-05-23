package com.powerbites.services;

import com.powerbites.entities.Venta;
import com.powerbites.repositories.VentaRepository;
import com.powerbites.repositories.VentaRepositoryImpl;
import java.util.List;

public class VentaService {

    private final VentaRepository saleRepository;

    public VentaService() {
        this.saleRepository = new VentaRepositoryImpl();
    }

    public void registerSale(Venta sale) {
        if (sale.getTotal() < 0) {
            System.out.println("\nError: El total de la venta no puede ser negativo.");
            return;
        }
        saleRepository.create(sale);
    }

    public Venta getById(int id) {
        if (id <= 0) {
            System.out.println("\nError: El ID introducido no es valido.");
            return null;
        }
        return saleRepository.readById(id);
    }

    public List<Venta> getAll() {
        return saleRepository.readAll();
    }

    public void modifySale(Venta sale) {
        if (saleRepository.readById(sale.getId()) == null) {
            System.out.println("\nError: La venta que intenta modificar no existe.");
            return;
        }
        saleRepository.refresh(sale);
    }

    public void deleteSale(int id) {
        if (saleRepository.readById(id) == null) {
            System.out.println("\nError: La venta que intenta eliminar no existe.");
            return;
        }
        saleRepository.delete(id);
    }
}