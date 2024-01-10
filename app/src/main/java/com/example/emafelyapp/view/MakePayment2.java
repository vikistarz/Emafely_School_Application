package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emafelyapp.R;

import com.example.emafelyapp.utility.AppConstant;

public class MakePayment2 extends AppCompatActivity {

    private EditText cardNumberEditText, expiryDateEditText, ccvEditText;
    private View backArrow;
    private Button payButton;

    private TextView userEmail, feeDetail, feeAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_payment2);

        inItView();
        inItListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String userEmailAddress  = mySharedPreference.getString(AppConstant.emailAddress, " ");
        String feesCost  = mySharedPreference.getString(AppConstant.feesPaid, " ");
        String paymentDetails = mySharedPreference.getString(AppConstant.paymentDetails, " ");

        userEmail.setText(userEmailAddress);
        payButton.setText("Pay " + "NGN " + feesCost);
        feeAmount.setText(feesCost);
        feeDetail.setText(paymentDetails);
    }

    public void inItView() {
        userEmail = findViewById(R.id.tv_email_address);
        cardNumberEditText = findViewById(R.id.et_card_number);
        expiryDateEditText = findViewById(R.id.et_card_date);
        ccvEditText = findViewById(R.id.et_card_cvv);
        feeDetail = findViewById(R.id.tv_fee_details);
        feeAmount = findViewById(R.id.tv_fee_amount);
        payButton = findViewById(R.id.btn_pay);
        backArrow = findViewById(R.id.arrow_back);

    }
    public void inItListener() {
        backArrow();
        payButton();
    }

    private void payButton() {
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MakePayment2.this,PaymentSummary.class);
                startActivity(myIntent);
            }
        });
    }

    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MakePayment2.super.onBackPressed();
            }
        });
    }


}