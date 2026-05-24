package com.powerbites.util;

import com.powerbites.entities.Cliente;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestorExportacion {

    public static void exportClientsCSV(List<Cliente> clients) {
        String fileName = "Listado Clientes.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write("ID,Nombre,Email,Telefono,Direccion");
            writer.newLine();

            for (Cliente c : clients) {
                writer.write(c.getId() + "," +
                        c.getName() + "," +
                        c.getEmail() + "," +
                        c.getPhone() + "," +
                        c.getAdress());
                writer.newLine();
            }
            System.out.println("\n¡Datos exportados correctamente al archivo: " + fileName + "!");

        } catch (IOException e) {
            System.err.println("\nError al exportar los datos: " + e.getMessage());
        }
    }
}