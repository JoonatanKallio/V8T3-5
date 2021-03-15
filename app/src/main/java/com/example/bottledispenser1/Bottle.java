package com.example.bottledispenser1;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double price;
    private double size;
    protected int stock;

    public Bottle(String name1, String manuf, double totE, double size1, double price1, int stock1){
        name = name1;
        manufacturer = manuf;
        total_energy = totE;
        price = price1;
        size = size1;
        stock = stock1;
    }

    public double getPrice(){
        return price;
    }

    public double getSize(){
        return size;
    }

    public int getStock() { return stock; }

    public void setStock(int stock1) { stock = stock1; }

    public String getName(){
        return name;
    }
}
