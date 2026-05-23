package com.powerbites.entities;

public class DetalleVenta {
    private int id;
    private int saleId;
    private int productId;
    private int amount;
    private double currentPrice;

    public DetalleVenta(int id, int saleId, int productId, int amount, double currentPrice) {
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.amount = amount;
        this.currentPrice = currentPrice;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSaleId() { return saleId; }
    public void setVentaId(int saleId) { this.saleId = saleId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public void showDetails() {
        System.out.println("\n LINEA DE DETALLE #" + id + "\n");
        System.out.println("ID Venta Asociada: " + saleId);
        System.out.println("ID Producto: " + productId);
        System.out.println("Cantidad: " + amount + " unidades.");
        System.out.println("Precio Unitario: " + currentPrice + " euros.");
        System.out.println("Subtotal de linea: " + (amount * currentPrice) + " euros.\n");
    }
}