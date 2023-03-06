package com.example.uberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class EstimateAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);

        SharedPreferences prefShare = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefShare.edit();
        double baseFee = prefShare.getInt("baseFee", 0);
        double mileRate = 3;
        double smtFee = prefShare.getInt("smartFee", 0);
        double miniFee = prefShare.getInt("miniFee", 0);
//        double finalPrice;
        RadioButton btnMini = (RadioButton)findViewById(R.id.btnMini);
        RadioButton btnSedan = (RadioButton)findViewById(R.id.btnSedan);
        RadioButton btnSUV = (RadioButton)findViewById(R.id.btnSuv);
        RadioButton btnSmart = (RadioButton)findViewById(R.id.btnSmart);
        TextView estimate = (TextView)findViewById(R.id.EstView);
        TextInputEditText mileAge = (TextInputEditText)findViewById(R.id.Mileage);
        TextView decimal = (TextView)findViewById(R.id.DecView);
        RadioGroup radGrp = (RadioGroup)findViewById(R.id.radioGroup);
        Button esti = (Button)findViewById(R.id.Estbutton);
        Button submit = (Button)findViewById(R.id.submitBtn);
        Button back = (Button)findViewById(R.id.backBtn);
        mileAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mileAge.getText().toString() != "")
                {
                    esti.setVisibility(View.VISIBLE);
                }
            }
        });
        esti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                esti.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
                int radioButtonID = radGrp.getCheckedRadioButtonId();
                View radioButton = radGrp.findViewById(radioButtonID);
                int idx = radGrp.indexOfChild(radioButton);
                RadioButton r = (RadioButton) radGrp.getChildAt(idx);
                Integer miles = Integer.parseInt(mileAge.getText().toString());
                double finalPrice = mileRate * miles;
                double dotPrice = 25 * miles;
                if (miles >= 4) {
                    for (int k = 0; k < miles / 4; k++) {
                        dotPrice -= 100;
                        finalPrice+= 1;
                    }
                }
                finalPrice += baseFee;
                if (r.getText().toString() == "Minivan")
                {
                    finalPrice += miniFee;
                }
                else if (r.getText().toString() == "Smart Car")
                {
                    finalPrice += smtFee;
                }
                Integer price = (int) finalPrice;
                estimate.setText(price.toString());
                Integer dot = (int)dotPrice;
                decimal.setText("." + dot.toString());
                editor.putInt("totalFee1", price);
                editor.putInt("totalFee2", dot);
                editor.apply();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstimateAct.this, ConfirmAct.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstimateAct.this, MainActivity.class));
            }
        });
    }
}