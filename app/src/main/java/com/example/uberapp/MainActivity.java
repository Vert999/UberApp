package com.example.uberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button estButton = (Button)findViewById(R.id.estButton);
        estButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EstimateAct.class));
            }
        });
        SharedPreferences sharePref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();
        editor.putInt("baseFee", 3);
        editor.putInt("smartFee", 2);
        editor.putInt("miniFee", 5);
        editor.apply();
    }
}