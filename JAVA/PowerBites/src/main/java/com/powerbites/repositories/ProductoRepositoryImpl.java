package com.powerbites.repositories;

import com.powerbites.entities.Producto;
import com.powerbites.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements ProductoRepository {

    @Override
    public void crear(Producto producto) {
        String sql = "INSERT INTO PRODUCTOS (nombre, descripcion, precio, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setString(4, producto.getCategoria());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto añadido al catalogo exitosamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
        }
    }

    @Override
    public Producto leerPorId(int id) {
        Producto producto = null;
        String sql = "SELECT * FROM PRODUCTOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
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
        return producto;
    }

    @Override
    public List<Producto> leerTodos() {
        List<Producto> productos = new ArrayList<>();
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
                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el catalogo de productos: " + e.getMessage());
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE PRODUCTOS SET nombre = ?, descripcion = ?, precio = ?, categoria = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setString(4, producto.getCategoria());
            stmt.setInt(5, producto.getId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Los datos del producto se han actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM PRODUCTOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto eliminado del catalogo.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
}