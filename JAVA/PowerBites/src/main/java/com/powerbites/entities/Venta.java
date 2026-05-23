package com.powerbites.entities;

import java.sql.Date;

public class Venta {
    private int id;
    private int clienteId;
    private int usuarioId;
    private Date fecha;
    private String estado;
    private double total;

    public Venta(int id, int clienteId, int usuarioId, Date fecha, String estado, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public void mostrarDetalles() {
        System.out.println("=== VENTA #" + id + " ===");
        System.out.println("ID Cliente: " + clienteId);
        System.out.println("ID Comercial (Usuario): " + usuarioId);
        System.out.println("Fecha: " + fecha.toString());
        System.out.println("Estado: " + estado);
        System.out.println("Total: " + total + " euros");
        System.out.println("==================");
    }
}