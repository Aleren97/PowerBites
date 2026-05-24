package com.powerbites.entities;

public class Usuario extends Persona {
    private String rol;
    private String passwordHash;

    public Usuario(int id, String nombre, String email, String rol, String passwordHash) {
        super(id, nombre, email);
        this.rol = rol;
        this.passwordHash = passwordHash;
    }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    @Override
    public void showDetails() {
        System.out.println("\n USUARIO COMERCIAL \n");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + name);
        System.out.println("Email: " + email);
        System.out.println("Rol: " + rol + "\n");
    }
}
