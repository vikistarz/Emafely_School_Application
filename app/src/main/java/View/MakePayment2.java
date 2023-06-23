package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emafelyapp.R;

public class MakePayment2 extends AppCompatActivity {

    private EditText cardNumberEditText, expiryDateEditText, ccvEditText;
    private View backArrow;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_payment2);

        inItView();
        inItListener();
    }

    public void inItView() {
        cardNumberEditText = findViewById(R.id.et_card_number);
        expiryDateEditText = findViewById(R.id.et_card_date);
        ccvEditText = findViewById(R.id.et_card_cvv);
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
                Intent myIntent = new Intent(MakePayment2.this, MakePayment1.class);
                startActivity(myIntent);
            }
        });
    }


}