package com.powerbites.entities;

public class DetalleVenta {
    private int id;
    private int ventaId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;

    public DetalleVenta(int id, int ventaId, int productoId, int cantidad, double precioUnitario) {
        this.id = id;
        this.ventaId = ventaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVentaId() { return ventaId; }
    public void setVentaId(int ventaId) { this.ventaId = ventaId; }

    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public void mostrarDetalles() {
        System.out.println("=== LINEA DE DETALLE #" + id + " ===");
        System.out.println("ID Venta Asociada: " + ventaId);
        System.out.println("ID Producto: " + productoId);
        System.out.println("Cantidad: " + cantidad + " unidades");
        System.out.println("Precio Unitario: " + precioUnitario + " euros");
        System.out.println("Subtotal de linea: " + (cantidad * precioUnitario) + " euros");
        System.out.println("===========================");
    }
}