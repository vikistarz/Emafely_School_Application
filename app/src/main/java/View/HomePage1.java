package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emafelyapp.R;

public class HomePage1 extends AppCompatActivity {

    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page1);

        inItView();
        inItListener();
    }
    public void inItView(){
        getStartedButton = findViewById(R.id.btn_get_started);
    }
    public void inItListener(){
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomePage1.this, HomePage2.class);
                startActivity(myIntent);
            }
        });
    }
}