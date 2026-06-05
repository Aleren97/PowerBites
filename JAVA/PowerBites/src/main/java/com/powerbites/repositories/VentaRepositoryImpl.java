package com.powerbites.repositories;

import com.powerbites.entities.Venta;
import com.powerbites.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaRepositoryImpl implements VentaRepository {

    @Override
    public void create(Venta sale) {
        String sql = "INSERT INTO VENTAS (cliente_id, usuario_id, fecha, estado, total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sale.getClientId());
            stmt.setInt(2, sale.getUserId());
            stmt.setDate(3, sale.getDate());
            stmt.setString(4, sale.getStatus());
            stmt.setDouble(5, sale.getTotal());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Venta registrada en el sistema exitosamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar la venta. Compruebe que el Cliente y Usuario existen: " + e.getMessage());
        }
    }

    @Override
    public Venta readById(int id) {
        Venta sale = null;
        String sql = "SELECT * FROM VENTAS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sale = new Venta(
                            rs.getInt("id"),
                            rs.getInt("cliente_id"),
                            rs.getInt("usuario_id"),
                            rs.getDate("fecha"),
                            rs.getString("estado"),
                            rs.getDouble("total")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar la venta por ID: " + e.getMessage());
        }
        return sale;
    }

    @Override
    public List<Venta> readAll() {
        List<Venta> sales = new ArrayList<>();
        String sql = "SELECT * FROM VENTAS";

        try (Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Venta v = new Venta(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("fecha"),
                        rs.getString("estado"),
                        rs.getDouble("total")
                );
                sales.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el registro de ventas: " + e.getMessage());
        }
        return sales;
    }

    @Override
    public void update(Venta sale) {
        String sql = "UPDATE VENTAS SET cliente_id = ?, usuario_id = ?, fecha = ?, estado = ?, total = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sale.getClientId());
            stmt.setInt(2, sale.getUserId());
            stmt.setDate(3, sale.getDate());
            stmt.setString(4, sale.getStatus());
            stmt.setDouble(5, sale.getTotal());
            stmt.setInt(6, sale.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Los datos de la venta se han actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar la venta: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM VENTAS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Venta eliminada del sistema.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar la venta. Es posible que tenga detalles asociados: " + e.getMessage());
        }
    }
}