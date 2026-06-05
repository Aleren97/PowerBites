package com.powerbites.repositories;

import com.powerbites.entities.Usuario;
import com.powerbites.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public void create(Usuario user) {
        String sql = "INSERT INTO USUARIOS (nombre, email, rol, password_hash) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRol());
            stmt.setString(4, user.getPasswordHash());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario comercial registrado con exito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario readById(int id) {
        Usuario user = null;
        String sql = "SELECT * FROM USUARIOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("rol"),
                            rs.getString("password_hash")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el usuario por ID: " + e.getMessage());
        }
        return user;
    }

    @Override
    public List<Usuario> readAll() {
        List<Usuario> users = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS";

        try (Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("rol"),
                        rs.getString("password_hash")
                );
                users.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de usuarios: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void update(Usuario user) {
        String sql = "UPDATE USUARIOS SET nombre = ?, email = ?, rol = ?, password_hash = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRol());
            stmt.setString(4, user.getPasswordHash());
            stmt.setInt(5, user.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Los datos del usuario se han actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM USUARIOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario eliminado del sistema.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}