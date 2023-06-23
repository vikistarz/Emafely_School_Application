package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.emafelyapp.R;

public class ResultPage extends AppCompatActivity {

    private View backArrow;
    private LinearLayout profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);

        inItView();
        inItListener();
    }

    public void inItView() {

       profile = findViewById(R.id.layout_profile);
        backArrow = findViewById(R.id.arrow_back);

    }

    public void inItListener() {

        profile();
        backArrow();
    }

    private void profile() {

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ResultPage.this, ProfilePage.class);
                startActivity(myIntent);
            }
        });
    }

    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ResultPage.this, StudentDashboard.class);
                startActivity(myIntent);
            }
        });
    }
}