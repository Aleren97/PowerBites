package com.powerbites.entities;

public class Producto {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;

    public Producto(int id, String name, String description, double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }


    @Override
    public String toString() {
        return "\n Producto " +id + "\n" +
                ", Nombre= '" + name + '\'' +
                ", Descripción= '" + description + '\'' +
                ", Precio= " + price + " euros" +
                ", Categoría= '" + category + '\'' +
                '\n';
    }
}