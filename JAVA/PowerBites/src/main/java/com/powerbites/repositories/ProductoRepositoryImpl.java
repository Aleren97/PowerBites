package com.powerbites.repositories;

import com.powerbites.entities.Producto;
import com.powerbites.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements ProductoRepository {

    @Override
    public void create(Producto product) {
        String sql = "INSERT INTO PRODUCTOS (nombre, descripcion, precio, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Producto añadido al catalogo exitosamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
        }
    }

    @Override
    public Producto readById(int id) {
        Producto product = null;
        String sql = "SELECT * FROM PRODUCTOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getString("categoria")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el producto por ID: " + e.getMessage());
        }
        return product;
    }

    @Override
    public List<Producto> readAll() {
        List<Producto> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS";

        try (Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getString("categoria")
                );
                products.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el catalogo de productos: " + e.getMessage());
        }
        return products;
    }

    @Override
    public void update(Producto product) {
        String sql = "UPDATE PRODUCTOS SET nombre = ?, descripcion = ?, precio = ?, categoria = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());
            stmt.setInt(5, product.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Los datos del producto se han actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM PRODUCTOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Producto eliminado del catalogo.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
}