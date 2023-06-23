package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.emafelyapp.R;

public class Announcement extends AppCompatActivity {

    private View backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement);

        inItView();
        inItListener();
    }
    public void inItView() {

        backArrow = findViewById(R.id.arrow_back);
    }
    public void inItListener() {

        backArrow();
    }

    private void backArrow() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Announcement.this, StudentDashboard.class);
                startActivity(myIntent);
            }
        });
    }
}