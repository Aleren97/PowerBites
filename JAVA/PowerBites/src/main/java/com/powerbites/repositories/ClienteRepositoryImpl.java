package com.powerbites.repositories;

import com.powerbites.entities.Cliente;
import com.powerbites.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public void create(Cliente client) {
        String sql = "INSERT INTO CLIENTES (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setClienteParameters(stmt, client);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente guardado en la base de datos exitosamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> readAll() {
        List<Cliente> clients = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTES";

        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(mapResultSetToCliente(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los clientes: " + e.getMessage());
        }
        return clients;
    }

    @Override
    public Cliente readById(int id) {
        Cliente client = null;
        String sql = "SELECT * FROM CLIENTES WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = mapResultSetToCliente(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el cliente por ID: " + e.getMessage());
        }
        return client;
    }




    @Override
    public void update(Cliente client) {
        String sql = "UPDATE CLIENTES SET nombre = ?, email = ?, telefono = ?, direccion = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setClienteParameters(stmt, client);

            stmt.setInt(5, client.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Los datos del cliente se han actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con ese ID para actualizar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CLIENTES WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Cliente eliminado de la base de datos.");
            } else {
                System.out.println("No se encontró ningún cliente con ese ID para eliminar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente findMejorCliente() {
        String sql = "SELECT cliente_id, SUM(total) AS total_gastado FROM VENTAS GROUP BY cliente_id " +
                "ORDER BY total_gastado DESC LIMIT 1";

        Cliente mejorCliente = null;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int idMejorCliente = rs.getInt("cliente_id");
                double totalGastado = rs.getDouble("total_gastado");

                mejorCliente = readById(idMejorCliente);

                if (mejorCliente != null) {
                    String RESET = "\u001B[0m";
                    String YELLOW_BOLD = "\u001B[1;33m";

                    System.out.println("¡Cálculo completado! El mejor cliente es " +
                            YELLOW_BOLD + mejorCliente.getName() + RESET +
                            " con un gasto total de " + totalGastado + "€.");
                }
            } else {
                System.out.println("No se han encontrado ventas registradas para calcular el mejor cliente.");
            }

        } catch (SQLException e) {
            System.err.println("Error al calcular el mejor cliente: " + e.getMessage());
        }

        return mejorCliente;
    }

    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("teléfono"),
                rs.getString("dirección")
        );
    }

    private void setClienteParameters(PreparedStatement stmt, Cliente client) throws SQLException {
        stmt.setString(1, client.getName());
        stmt.setString(2, client.getEmail());
        stmt.setString(3, client.getPhone());
        stmt.setString(4, client.getAdress());
    }
}