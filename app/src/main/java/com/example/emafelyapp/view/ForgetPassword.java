package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.emafelyapp.R;

public class ForgetPassword extends AppCompatActivity {

   private View arrowBack;
   private EditText emailAddressEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        inItViews();
        inItListener();

    }

    private void inItViews() {
        arrowBack = findViewById(R.id.arrow_back);
        emailAddressEditText = findViewById(R.id.et_email_address);

    }

    private void inItListener() {

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             ForgetPassword.super.onBackPressed();
            }
        });
    }

}