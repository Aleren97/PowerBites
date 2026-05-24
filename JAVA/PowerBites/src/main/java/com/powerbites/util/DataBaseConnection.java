package com.powerbites.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/powerbites";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection connection;

    private DataBaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("\n¡Conexión a la base de datos powerbites establecida con éxito!\n");
            } catch (ClassNotFoundException e) {
                System.err.println("\nError: Driver de MySQL no encontrado.\n");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("\nConexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
