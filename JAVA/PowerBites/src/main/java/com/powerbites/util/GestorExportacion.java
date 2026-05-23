package com.powerbites.util;

import com.powerbites.entities.Cliente;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestorExportacion {

    public static void exportarClientesCSV(List<Cliente> clientes) {
        String nombreArchivo = "Listado Clientes.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribimos la cabecera
            writer.write("ID,Nombre,Email,Telefono,Direccion");
            writer.newLine();

            for (Cliente c : clientes) {
                writer.write(c.getId() + "," +
                        c.getNombre() + "," +
                        c.getEmail() + "," +
                        c.getTelefono() + "," +
                        c.getDireccion());
                writer.newLine();
            }
            System.out.println("¡Datos exportados correctamente al archivo: " + nombreArchivo + "!");

        } catch (IOException e) {
            System.err.println("Error al exportar los datos: " + e.getMessage());
        }
    }
}