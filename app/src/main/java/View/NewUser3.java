package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.emafelyapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class NewUser3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText cityEditText;
    private TextInputEditText passwordEditText, confirmPasswordEdittext;
    private Spinner relationShipSpinner ;
    private CheckBox myCheckBox;
    private Button submitButton;

    String relationShipToChild [] = {" ", "Father", "Mother", "Uncle", "Aunt", "Grand Father", "Grand Mother", "cousin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user3);

        inItView();
        inItListener();
    }
    public void inItView() {
        cityEditText = findViewById(R.id.et_city);
        passwordEditText = findViewById(R.id.et_password);
        confirmPasswordEdittext = findViewById(R.id.et_confirm_password);
        myCheckBox = findViewById(R.id.check_box);
        relationShipSpinner = findViewById(R.id.spinner_relationship);
        submitButton = findViewById(R.id.btn_submit_details);

    }
    public void inItListener() {

          submitButton();
        relationShipSpinner();
    }

    private void relationShipSpinner() {
        relationShipSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, relationShipToChild);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
        relationShipSpinner.setAdapter(myArrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(), relationShipToChild[i], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void submitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(NewUser3.this, StudentDashboard.class);
                startActivity(myIntent);
            }
        });
    }
}