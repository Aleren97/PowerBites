package com.powerbites.entities;

public class Cliente extends Persona {
    private String phone;
    private String adress;

    public Cliente(int id, String name, String email, String phone, String adress) {
        super(id, name, email);
        this.phone = phone;
        this.adress = adress;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAdress() { return adress; }
    public void setAdress(String adress) { this.adress = adress; }

    @Override
    public void showDetails() {
        System.out.println("\n CLIENTE \n");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + name);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + phone);
        System.out.println("Dirección: " + adress + "\n");
    }
}
