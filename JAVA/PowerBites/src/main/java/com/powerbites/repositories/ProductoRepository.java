package com.powerbites.repositories;

import com.powerbites.entities.Producto;
import java.util.List;

public interface ProductoRepository {

    void create(Producto producto);

    Producto readById(int id);

    List<Producto> readAll();

    void refresh(Producto product);

    void delete(int id);
}