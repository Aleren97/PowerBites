package com.powerbites.services;

import com.powerbites.entities.Producto;
import com.powerbites.repositories.ProductoRepository;
import com.powerbites.repositories.ProductoRepositoryImpl;
import java.util.List;

public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService() {
        this.productoRepository = new ProductoRepositoryImpl();
    }

    public void registrarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            System.out.println("Error: El nombre del producto no puede estar vacio.");
            return;
        }
        if (producto.getPrecio() < 0) {
            System.out.println("Error: El precio no puede ser negativo.");
            return;
        }
        productoRepository.crear(producto);
    }

    public Producto buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("Error: El ID introducido no es valido.");
            return null;
        }
        return productoRepository.leerPorId(id);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.leerTodos();
    }

    public void modificarProducto(Producto producto) {
        if (productoRepository.leerPorId(producto.getId()) == null) {
            System.out.println("Error: El producto que intenta modificar no existe.");
            return;
        }
        productoRepository.actualizar(producto);
    }

    public void eliminarProducto(int id) {
        if (productoRepository.leerPorId(id) == null) {
            System.out.println("Error: El producto que intenta eliminar no existe.");
            return;
        }
        productoRepository.eliminar(id);
    }
}