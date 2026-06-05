package com.powerbites.repositories;

import com.powerbites.entities.DetalleVenta;
import com.powerbites.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaRepositoryImpl implements DetalleVentaRepository {

    @Override
    public void create(DetalleVenta detail) {
        String sql = "INSERT INTO DETALLE_VENTA (venta_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detail.getSaleId());
            stmt.setInt(2, detail.getProductId());
            stmt.setInt(3, detail.getAmount());
            stmt.setDouble(4, detail.getCurrentPrice());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Linea de detalle añadida a la venta exitosamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar el detalle. Compruebe que la Venta y el Producto existen: " + e.getMessage());
        }
    }

    @Override
    public DetalleVenta readById(int id) {
        DetalleVenta detail = null;
        String sql = "SELECT * FROM DETALLE_VENTA WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    detail = new DetalleVenta(
                            rs.getInt("id"),
                            rs.getInt("venta_id"),
                            rs.getInt("producto_id"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unitario")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el detalle por ID: " + e.getMessage());
        }
        return detail;
    }

    @Override
    public List<DetalleVenta> readAll() {
        List<DetalleVenta> details = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_VENTA";

        try (Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DetalleVenta d = new DetalleVenta(
                        rs.getInt("id"),
                        rs.getInt("venta_id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario")
                );
                details.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los detalles de venta: " + e.getMessage());
        }
        return details;
    }

    @Override
    public void update(DetalleVenta detail) {
        String sql = "UPDATE DETALLE_VENTA SET venta_id = ?, producto_id = ?, cantidad = ?, precio_unitario = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detail.getSaleId());
            stmt.setInt(2, detail.getProductId());
            stmt.setInt(3, detail.getAmount());
            stmt.setDouble(4, detail.getCurrentPrice());
            stmt.setInt(5, detail.getId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("El detalle de la venta se ha actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el detalle: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM DETALLE_VENTA WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Detalle de venta eliminado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el detalle de venta: " + e.getMessage());
        }
    }
}