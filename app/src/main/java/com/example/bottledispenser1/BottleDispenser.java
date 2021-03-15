package com.example.bottledispenser1;

import android.os.Bundle;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class BottleDispenser {
    private int bottles;
    private double money;
    private ArrayList<Bottle> bottle_array, receipt_array;
    private Bottle receiptBottle = null;

    private BottleDispenser() {
        bottles = 8;
        money = 0;

        bottle_array = new ArrayList<>();

        bottle_array.add(0, new Bottle("Pepsi", "Pepsi", 1.0, 0.3, 2, 2));
        bottle_array.add(1, new Bottle("Pepsi", "Pepsi", 1.0, 0.5, 2.5, 2));
        bottle_array.add(2, new Bottle("Pepsi Max", "Pepsi", 1.0, 0.3, 2, 2));
        bottle_array.add(3, new Bottle("Pepsi Max", "Pepsi", 1.0, 0.5, 2.5, 2));
        bottle_array.add(4, new Bottle("Coca-Cola", "Coca-Cola", 1.0, 0.3, 2, 2));
        bottle_array.add(5, new Bottle("Coca-Cola", "Coca-Cola", 1.0, 0.5, 2.5, 2));
        bottle_array.add(6, new Bottle("Coca-Cola Zero", "Coca-Cola", 1.0, 0.3, 2, 2));
        bottle_array.add(7, new Bottle("Coca-Cola Zero", "Coca-Cola", 1.0, 0.5, 2.5, 2));
    }

    private static BottleDispenser bd = new BottleDispenser();

    public static BottleDispenser getInstance(){
        return bd;
    }

    public void addMoney(int x) {
        money += x;
    }

    public String buyBottle(int userSelection) {
        String textReturn = null;
        if (bottle_array.isEmpty()) {
            textReturn = "Out of stock!";
        }
        else {
            Bottle selection = bottle_array.get(userSelection);
            if (selection.getStock() == 0) {
                textReturn = "Out of stock.";
            } else if (money < selection.getPrice()) {
                textReturn = "Add money first!";
            } else if (money >= selection.getPrice()) {
                bottles -= 1;
                money -= selection.getPrice();
                textReturn = "KACHUNK! " + selection.getName() + " " + selection.getSize() + "l came out of the dispenser!";
                int stock = selection.getStock() - 1;
                selection.setStock(stock);
                receiptBottle = selection;
            }
        }
        return textReturn;
    }

    public String returnMoney() {
        String text = "Klink klink. Money came out! You got " + money + "€ back";
        money = 0;
        return text;
    }

    public double getMoney() {
        return money;
    }

    public Bottle printReceipt(){
        return receiptBottle;
    }

}



