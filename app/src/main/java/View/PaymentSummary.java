package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.emafelyapp.R;

public class PaymentSummary extends AppCompatActivity {

    private View backArrow;
    private LinearLayout mainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_summary);

        inItView();
        inItListener();
    }

    public void inItView() {
        mainPage = findViewById(R.id.layout_main_page);
        backArrow = findViewById(R.id.arrow_back);
    }

    public void inItListener() {
        backArrow();
        mainPage();
    }

    private void mainPage() {
        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PaymentSummary.this,MainPage1.class);
                startActivity(myIntent);
            }
        });
    }

    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PaymentSummary.this, MakePayment2.class);
                startActivity(myIntent);
            }
        });
    }

}