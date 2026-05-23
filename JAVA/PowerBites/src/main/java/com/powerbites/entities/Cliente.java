package com.powerbites.entities;

public class Cliente extends Persona {
    private String telefono;
    private String direccion;

    public Cliente(int id, String nombre, String email, String telefono, String direccion) {
        super(id, nombre, email);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public void mostrarDetalles() {
        System.out.println("=== CLIENTE ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("===============");
    }
}
