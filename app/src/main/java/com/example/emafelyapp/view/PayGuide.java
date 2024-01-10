package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emafelyapp.R;

public class PayGuide extends AppCompatActivity {

    private View backArrow;
    private Button crecheButton, nurseryButton, preNurseryButton, primaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_guide);

        inItView();
        inItListener();
    }

    public void inItView() {
        crecheButton = findViewById(R.id.btn_creche);
        nurseryButton = findViewById(R.id.btn_nursery);
        preNurseryButton = findViewById(R.id.btn_pre_nursery);
        primaryButton = findViewById(R.id.btn_primary);
        backArrow = findViewById(R.id.arrow_back);
    }

    public void inItListener() {

        crecheButton();
        nurseryButton();
        preNurseryButton();
        primaryButton();
        backArrow();
    }

    private void primaryButton() {
        primaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PayGuide.this, MakePayment1.class);
                startActivity(myIntent);
            }
        });
    }

    private void preNurseryButton() {
        preNurseryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PayGuide.this, MakePayment1.class);
                startActivity(myIntent);
            }
        });
    }

    private void nurseryButton() {
        nurseryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PayGuide.this, MakePayment1.class);
                startActivity(myIntent);
            }
        });
    }

    private void crecheButton() {
        crecheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PayGuide.this, MakePayment1.class);
                startActivity(myIntent);
            }
        });

    }

    private void backArrow() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PayGuide.this, StudentDashboard.class);
                startActivity(myIntent);
            }
        });
    }


}