package com.powerbites.repositories;

import com.powerbites.entities.Usuario;
import com.powerbites.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public void crear(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (nombre, email, rol, password_hash) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getRol());
            stmt.setString(4, usuario.getPasswordHash());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario comercial registrado con exito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario leerPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
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
        return usuario;
    }

    @Override
    public List<Usuario> leerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
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
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET nombre = ?, email = ?, rol = ?, password_hash = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getRol());
            stmt.setString(4, usuario.getPasswordHash());
            stmt.setInt(5, usuario.getId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Los datos del usuario se han actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM USUARIOS WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado del sistema.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}