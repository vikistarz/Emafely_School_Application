package com.example.emafelyapp.view;

import static android.media.CamcorderProfile.get;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.emafelyapp.R;

import java.util.ArrayList;

import com.example.emafelyapp.adapters.PaymentAdapter;
import com.example.emafelyapp.utility.AppConstant;
import com.example.emafelyapp.utility.PaymentModel;
import com.example.emafelyapp.utility.RecyclerViewInterface;

public class MakePayment1 extends AppCompatActivity implements RecyclerViewInterface{

    private View backArrow;

    private TextView parentFullName;


    private RecyclerView myRecyclerView;

    ArrayList<PaymentModel> myPaymentModel = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_payment1);

        inItView();
        inItListener();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String parentName  = mySharedPreference.getString(AppConstant.parentName, " ");

        parentFullName.setText("Welcome  " + parentName);
    }

    private void inItView() {
        parentFullName = findViewById(R.id.tv_parent_name);
        backArrow = findViewById(R.id.arrow_back);
        myRecyclerView = findViewById(R.id.payment_recycler_view);

        myRecyclerView();
    }

    private void myRecyclerView() {

        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayoutManager);
        RecyclerView.Adapter myAdapter = new PaymentAdapter(this, myPaymentModel, this);

        myPaymentModel.add(new PaymentModel(false, "School Fees", "16000"));
        myPaymentModel.add(new PaymentModel(false, "Uniform", "5000"));
        myPaymentModel.add(new PaymentModel(false, "Text Books", "15000"));
        myPaymentModel.add(new PaymentModel(false, "Sport Wear", "3000"));
        myPaymentModel.add(new PaymentModel(false, "Excursion", "5000"));

        myRecyclerView.setAdapter(myAdapter);
    }

    private void inItListener() {
        backArrow();

    }


    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MakePayment1.super.onBackPressed();
            }
        });
    }




    @Override
    public void handleClickItem(PaymentModel myPaymentModel) {

        Intent myIntent = new Intent(MakePayment1.this, PaymentDetails.class);
        myIntent.putExtra("fees_detail", myPaymentModel.getFees());
        myIntent.putExtra("amount_charged", myPaymentModel.getAmount());
        startActivity(myIntent);

    }
}