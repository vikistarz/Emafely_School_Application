package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.emafelyapp.R;

public class MainPage1 extends AppCompatActivity {
    private LinearLayout learnAboutUs;
    private  View  arrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_1);
        inItView();
        inItListener();
    }

    public void inItView() {
        learnAboutUs = findViewById(R.id.layout_learn_about_us);
        arrowBack = findViewById(R.id.arrow_back);
    }

    private void inItListener() {
        learnAboutUs();
        arrowBack();
    }

    private void arrowBack() {
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainPage1. this, HomePage2.class);
                startActivity(myIntent);
            }
        });
    }

    private void learnAboutUs() {
        learnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainPage1. this, MainPage2.class);
                startActivity(myIntent);
            }
        });
    }


}