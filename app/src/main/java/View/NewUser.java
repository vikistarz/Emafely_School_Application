package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emafelyapp.R;

public class NewUser extends AppCompatActivity  {
    private EditText parentEditText, usernameEditText, emailAddressEditText, childNameEditText;
    private View backArrow;
    private LinearLayout nextArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        inItView();
        inItListener();
    }
    public void inItView() {
        parentEditText = findViewById(R.id.et_parent_name);
        usernameEditText = findViewById(R.id.et_username);
        emailAddressEditText = findViewById(R.id.et_email_address);
        childNameEditText = findViewById(R.id.et_child_name);
        nextArrow = findViewById(R.id.linear_layout_next);
        backArrow = findViewById(R.id.arrow_back);

    }

    public void inItListener() {

        nexArrow();
        backArrow();
    }

    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(NewUser.this, MainPage2.class);
                startActivity(myIntent);
            }
        });
    }

    private void nexArrow() {
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(NewUser.this, NewUser2.class);
                startActivity(myIntent);
            }
        });
    }


}