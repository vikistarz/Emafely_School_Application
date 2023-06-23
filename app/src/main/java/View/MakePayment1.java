package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.emafelyapp.R;

public class MakePayment1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private View backArrow;
    private Button makePaymentButton;
    private CheckBox schoolFeesBox, uniformBox, textBookBox, sportWearBox, excursionBox, termsConditionBox;
    private Spinner paymentSpinner;

    String paymentVia [] = {"MasterCard","Verve","Visa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_payment1);

        inItView();
        inItListener();
    }

    private void inItView() {
        backArrow = findViewById(R.id.arrow_back);
        schoolFeesBox = findViewById(R.id.school_fees_box);
        uniformBox = findViewById(R.id.uniform_box);
        textBookBox = findViewById(R.id.text_book_box);
        sportWearBox = findViewById(R.id.sport_wear_box);
        excursionBox = findViewById(R.id.excursion_box);
        termsConditionBox = findViewById(R.id.terms_condition_box);
        makePaymentButton = findViewById(R.id.btn_make_payment);
        paymentSpinner = findViewById(R.id.spinner_make_payment);

    }
    private void inItListener() {
        backArrow();
        makePaymentButton();
        paymentSpinner();
    }


    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MakePayment1.this, MakePayment2.class);
                startActivity(myIntent);
            }
        });
    }

    private void paymentSpinner() {
        paymentSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this, R.layout.list_item3, paymentVia);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item3);
        paymentSpinner.setAdapter(myArrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void makePaymentButton() {
        makePaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MakePayment1. this, MakePayment2.class);
                startActivity(myIntent);
            }
        });
    }

}