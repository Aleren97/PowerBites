package com.powerbites.entities;

import java.sql.Date;

public class Venta {
    private int id;
    private int clientId;
    private int userId;
    private Date date;
    private String status;
    private double total;

    public Venta(int id, int clientId, int userId, Date date, String status, double total) {
        this.id = id;
        this.clientId = clientId;
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public void showDetails() {
        System.out.println("\n VENTA #" + id + "\n");
        System.out.println("ID Cliente: " + clientId);
        System.out.println("ID Comercial (Usuario): " + userId);
        System.out.println("Fecha: " + date.toString());
        System.out.println("Estado: " + status);
        System.out.println("Total: " + total + " euros\n");
    }
}