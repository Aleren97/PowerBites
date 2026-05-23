package com.powerbites.repositories;

import com.powerbites.entities.DetalleVenta;
import java.util.List;

public interface DetalleVentaRepository {

    void create(DetalleVenta detail);

    DetalleVenta readById(int id);

    List<DetalleVenta> readAll();

    void refresh(DetalleVenta detail);

    void delete(int id);
}