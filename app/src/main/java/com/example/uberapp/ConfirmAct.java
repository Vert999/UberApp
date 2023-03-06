package com.example.uberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ConfirmAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        SharedPreferences prefShare = getSharedPreferences("MyPref", MODE_PRIVATE);
        Integer price1 = prefShare.getInt("totalFee1", 0);
        Integer price2 = prefShare.getInt("totalFee2", 0);
        String priceText = price1.toString() + "." + price2.toString();
        String[] names = new String[]{"Bart Simpson", "Ryan Gosling", "Miles Prower", "Dominic Toretto", "Paul Walker", "Speed Racer"};
        Random rand = new Random();
        int randDrv = rand.nextInt(6);
        String driver = names[randDrv];
        TextView textV = (TextView)findViewById(R.id.textView9);
        TextView eta = (TextView)findViewById(R.id.textView10);
        TextView total = (TextView)findViewById(R.id.textView11);
        total.setText(priceText);
        textV.setText("Your driver " + driver + " will be arriving soon!");
        if (randDrv == 4)
        {
            String etaTxt = "N/A";
            eta.setText("Estimated Arrival Time: " + etaTxt);
        }
        else {
            Integer timeA = rand.nextInt(60);
            String etaTxt = timeA.toString();
            eta.setText("Estimated Arrival Time: " + etaTxt + " Minutes!");
        }

    }
}