package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emafelyapp.R;

import java.util.Timer;
import java.util.TimerTask;

import Utility.AppConstant;

public class PaymentDetails extends AppCompatActivity {

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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences mySharedPreferences = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putString(AppConstant.feesPaid, feesEditText.getText().toString().trim());
        myEditor.putString(AppConstant.paymentDetails, feesDetails.getText().toString().trim());
        myEditor.apply();
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
        feesEditText = findViewById(R.id.et_fees);
        termsConditionBox = findViewById(R.id.terms_condition_box);
        makePaymentButton = findViewById(R.id.btn_make_payment);

        Intent myIntent = getIntent();
        String fees = myIntent.getStringExtra("fees_detail");
        String amount = myIntent.getStringExtra("amount_charged");


        // setText on appropriate position

        feesDetails.setText(fees);
        totalCost.setText(amount);
    }

    private void inItListener() {
        backArrow();
        makePaymentButton();

    }

    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PaymentDetails.this, MakePayment1.class);
                startActivity(myIntent);
            }
        });
    }

    private void makePaymentButton() {
        makePaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myProgressBar.setVisibility(View.VISIBLE);

                Timer myTimer = new Timer();
                TimerTask myTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                        myProgressBar.setProgress(counter);

                        if(counter == 30){
                            myTimer.cancel();
                        }
                    }
                };
                myTimer.schedule(myTimerTask, 30, 30);
                if(validateMakePayment() & validateCheckbox()){
                    Intent myIntent = new Intent(PaymentDetails. this, MakePayment2.class);
                    startActivity(myIntent);
                }

            }
        });
    }

    public boolean validateMakePayment(){
        String retrievalMakePayment = feesEditText.getText().toString();

        if(retrievalMakePayment.isEmpty()){
            feesEditText.setError("Payment field empty");
            return false;
        }
        return true;
    }

    public boolean validateCheckbox(){
        if(!termsConditionBox.isChecked()){
            Toast.makeText(this, "tick before you pay", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}