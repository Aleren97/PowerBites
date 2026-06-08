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


    @Override
    public String toString() {
        return "\n DetalleVenta " + "id= " +id + "\n"+
                "saleId= " + saleId +
                ", productId= " + productId +
                ", amount= " + amount +
                ", currentPrice= " + currentPrice +
                '\n';
    }
}