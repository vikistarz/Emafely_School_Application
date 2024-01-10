package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emafelyapp.R;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.example.emafelyapp.utility.AppConstant;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RaveUiManager;
import com.flutterwave.raveandroid.rave_java_commons.RaveConstants;

public class PaymentDetails extends AppCompatActivity {
    private ProgressDialog myProgressDialog;
    private String email = "emiewovictor@gmail.com";
    private String currency = "NGN";
    private String fName = "Victor";
    private String lName = "Emiewo";
    private String publicKey = "FLWPUBK_TEST-b30be7791cef024bf021c89d2fd10d92-X";
    private String encryptionkey = "FLWSECK_TEST1667b56f382d";

    private static final String TAG = "RAVI_PAYMENT";

    private View backArrow;
    private Button makePaymentButton;
    private TextView parentFullName, feesDetails, totalCost;
    private EditText feesEditText;
    private ProgressBar myProgressBar;
    int counter = 0;

    CheckBox termsConditionBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_details);


        inItView();
        inItListener();
    }



    protected void onResume() {
        super.onResume();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String parentName  = mySharedPreference.getString(AppConstant.parentName, " ");

        parentFullName.setText(parentName);
    }
    private void inItView() {
        parentFullName = findViewById(R.id.tv_parent_name);
        backArrow = findViewById(R.id.arrow_back);
        feesDetails = findViewById(R.id.tv_fee_details);
        totalCost = findViewById(R.id.tv_total_cost);
        myProgressBar = findViewById(R.id.progress_bar);
        termsConditionBox = findViewById(R.id.terms_condition_box);
        makePaymentButton = findViewById(R.id.btn_make_payment);

        Intent myIntent = getIntent();
        String fees = myIntent.getStringExtra("fees_detail");
        String amount = myIntent.getStringExtra("amount_charged");


        // setText on appropriate position

        feesDetails.setText(fees);
        totalCost.setText(amount);

        myProgressDialog = new ProgressDialog(this);
        myProgressDialog.setMessage("Please Wait");
        myProgressDialog.setCancelable(false);
    }

    private void inItListener() {
        backArrow();
        makePaymentButton();

    }

    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PaymentDetails. this, StudentDashboard.class);
                startActivity(myIntent);
            }
        });
    }

    private void makePaymentButton() {
        makePaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateCheckbox()){
                    makePayment();


                }

            }
        });
    }

    private void makePayment() {
        myProgressDialog.show();
        UUID myUUID = UUID.randomUUID();

        new RaveUiManager(this).setAmount(round(Integer.parseInt(totalCost.getText().toString().substring(4)),2))
                .setCurrency(currency)
                .setEmail(email)
                .setfName(fName)
                .setlName(lName)
//                .setNarration(narration)
                .setPublicKey(publicKey)
                .setEncryptionKey(encryptionkey)
                .setTxRef(myUUID.toString())
//                .setPhoneNumber(phoneNumber, true)
                .acceptAccountPayments(true)
                .acceptCardPayments(true)
                .acceptMpesaPayments(true)
                .acceptAchPayments(true)
                .acceptGHMobileMoneyPayments(false)
                .acceptUgMobileMoneyPayments(false)
                .acceptZmMobileMoneyPayments(false)
                .acceptRwfMobileMoneyPayments(false)
                .acceptSaBankPayments(false)
                .acceptUkPayments(false)
                .acceptBankTransferPayments(true)
                .acceptUssdPayments(true)
                .acceptBarterPayments(true)
                .acceptFrancMobileMoneyPayments(false, null)
                .allowSaveCardFeature(true)
                .onStagingEnv(true)
//                  .setMeta(List<Meta>)
//                  .withTheme(styleId)
                .isPreAuth(false)
//                    .setSubAccounts(List<SubAccount>)
                .shouldDisplayFee(true)
                .showStagingLabel(true)
                .initialize();
    }

    public static float round(float d, int decimalPlace){
        BigDecimal myBigDecimal = new BigDecimal(Float.toString(d));
        myBigDecimal = myBigDecimal.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return myBigDecimal.floatValue();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        myProgressDialog.dismiss();
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Log.d(TAG, "onActivityResult: " + "SUCCESS" + message );
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Log.d(TAG, "onActivityResult: " + "ERROR" + message );
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Log.d(TAG, "onActivityResult: " + "CANCELLED" + message );
                Toast.makeText(this, "CANCELLED " , Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public boolean validateCheckbox(){
        if(!termsConditionBox.isChecked()){
            Toast.makeText(this, "tick before you pay", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(PaymentDetails. this, StudentDashboard.class);
                    startActivity(myIntent);
    }
}