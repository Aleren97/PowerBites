package com.powerbites.repositories;

import com.powerbites.entities.Producto;
import java.util.List;

public interface ProductoRepository {

    void crear(Producto producto);

    Producto leerPorId(int id);

    List<Producto> leerTodos();

    void actualizar(Producto producto);

    void eliminar(int id);
}