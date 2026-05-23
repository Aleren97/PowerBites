package com.powerbites.repositories;

import com.powerbites.entities.Venta;
import java.util.List;

public interface VentaRepository {

    void create(Venta sale);

    Venta readById(int id);

    List<Venta> readAll();

    void refresh(Venta sale);

    void delete(int id);
}