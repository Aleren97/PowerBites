package com.powerbites.repositories;

import com.powerbites.entities.Venta;
import java.util.List;

public interface VentaRepository {

    void crear(Venta venta);

    Venta leerPorId(int id);

    List<Venta> leerTodos();

    void actualizar(Venta venta);

    void eliminar(int id);
}