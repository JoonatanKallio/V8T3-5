package com.example.bottledispenser1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private BottleDispenser bd;
    private TextView textView, bottleDispense, moneyText;
    private SeekBar seekBar;
    protected Spinner spinner, spinner1;
    public int intReturn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = BottleDispenser.getInstance();
        //Casting the different objects
        textView = (TextView) findViewById(R.id.MoneyText);
        moneyText = (TextView) findViewById(R.id.moneytext);
        bottleDispense = (TextView) findViewById(R.id.bottleDispense);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner2);


        //Dropdown menu for the different bottles
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bottles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.bottlesize, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);



        //Progressbar for adding money to the machine
        String text = seekBar.getProgress() + "€";
        moneyText.setText(text);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int moneys;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        moneys = progress;
                        moneyText.setText("" + progress + "€");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        moneyText.setText(seekBar.getProgress() + "€");
                    }
                }
        );
    }

    public void addMoney(View v) {
        bd.addMoney(seekBar.getProgress());
        seekBar.setProgress(0);
        String money = String.valueOf(bd.getMoney());
        textView.setText("Current balance: " + money + "€");

    }

    public void returnMoney(View v) {
        bottleDispense.setText(bd.returnMoney());
        String money = String.valueOf(bd.getMoney());
        textView.setText("Current balance: " + money + "€");
    }

    public void printReceipt(View v) {
        Bottle bottle = bd.printReceipt();
        if (bottle == null) {
            bottleDispense.setText("Buy something first.");
        } else {
            String name = bottle.getName();
            double size = bottle.getSize();
            double price = bottle.getPrice();
            String text = "Receipt:\nYou bought " + name + " " + size + "l, and it cost " + price + "€.";
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput("Receipt.txt", MODE_PRIVATE);
                fileOutputStream.write(text.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        Log.e("IOExpection", "Error occurred while trying to close the file.");
                    }
                }
                bottleDispense.setText("Receipt printed.");
            }
        }
    }

    public void buyBottle(View v) {
        String text2 = bd.buyBottle(intReturn);
        bottleDispense.setText(text2);
        String money = String.valueOf(bd.getMoney());
        textView.setText("Current balance: " + money + "€");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text1 = spinner.getSelectedItem().toString();
        String text2 = spinner1.getSelectedItem().toString();


        if ((text1.equals("Pepsi")) && (text2.equals("0.3l"))) {
            intReturn = 0;
        } else if ((text1.equals("Pepsi")) && (text2.equals("0.5l"))) {
            intReturn = 1;
        } else if (text1.equals("Pepsi Max") && text2.equals("0.3l")) {
            intReturn = 2;
        } else if (text1.equals("Pepsi Max") && text2.equals("0.5l")) {
            intReturn = 3;
        } else if (text1.equals("Coca-Cola") && text2.equals("0.3l")) {
            intReturn = 4;
        } else if (text1.equals("Coca-Cola") && text2.equals("0.5l")) {
            intReturn = 5;
        } else if (text1.equals("Coca-Cola Zero") && text2.equals("0.3l")) {
            intReturn = 6;
        } else if (text1.equals("Coca-Cola Zero") && text2.equals("0.5l")) {
            intReturn = 7;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }



}




