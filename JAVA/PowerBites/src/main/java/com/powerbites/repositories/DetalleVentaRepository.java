package com.powerbites.repositories;

import com.powerbites.entities.DetalleVenta;
import java.util.List;

public interface DetalleVentaRepository {

    void crear(DetalleVenta detalle);

    DetalleVenta leerPorId(int id);

    List<DetalleVenta> leerTodos();

    void actualizar(DetalleVenta detalle);

    void eliminar(int id);
}