package com.powerbites.services;

import com.powerbites.entities.Producto;
import com.powerbites.repositories.ProductoRepository;
import com.powerbites.repositories.ProductoRepositoryImpl;
import java.util.List;

public class ProductoService {

    private final ProductoRepository productRepository;

    public ProductoService() {
        this.productRepository = new ProductoRepositoryImpl();
    }

    public void registerProduct(Producto product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            System.out.println("\nError: El nombre del producto no puede estar vacio.");
            return;
        }
        if (product.getPrice() < 0) {
            System.out.println("\nError: El precio no puede ser negativo.");
            return;
        }
        productRepository.create(product);
    }

    public Producto getById(int id) {
        if (id <= 0) {
            System.out.println("\nError: El ID introducido no es valido.");
            return null;
        }
        return productRepository.readById(id);
    }

    public List<Producto> getAll() {
        return productRepository.readAll();
    }

    public void modifyProduct(Producto product) {
        if (productRepository.readById(product.getId()) == null) {
            System.out.println("\nError: El producto que intenta modificar no existe.");
            return;
        }
        productRepository.refresh(product);
    }

    public void deleteProduct(int id) {
        if (productRepository.readById(id) == null) {
            System.out.println("\nError: El producto que intenta eliminar no existe.");
            return;
        }
        productRepository.delete(id);
    }
}