package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emafelyapp.R;

import java.util.Calendar;

public class NewUser2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText homeAddressEditText, phoneNumberEditText, dateOfBirth;
    private Spinner sexSpinner, stateSpinner;
    private View arrowBack;
    private LinearLayout nextArrow;

    private DatePickerDialog myDatePickerDialog;




    String sex [] = {" ", "Male", "Female"};



    String states [] = {" ","Abia", "Adamawa", " Akwa Ibom"," Anambra", "Bauchi"," Bayelsa", "Benue", " Borno", "Cross River",
                     "Delta", "Ebonyi", "Edo", "Ekiti", "Enugu", "Gombe", "Imo", "Jigawa", "Kaduna", " Kano", " Katsina",
                      "Kebbi", "Kogi", "Kwara", "Lagos", "Nasarawa", "Niger", "Ogun" , "Ondo", "Osun", "Oyo", "Plateau",
                        "Rivers", "Sokoto", "Taraba","Yobe", "Zamfara", "FCT" };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user2);

        inItView();
        inItListener();
    }
    public void inItView() {
        homeAddressEditText = findViewById(R.id.et_home_address);
        phoneNumberEditText = findViewById(R.id.et_home_phone);
        dateOfBirth = findViewById(R.id.et_date_of_birth);
        sexSpinner = findViewById(R.id.spinner_sex);
        stateSpinner = findViewById(R.id.spinner_state);
        nextArrow = findViewById(R.id.layout_next);
        arrowBack = findViewById(R.id.arrow_back);
    }
    public void inItListener() {

        nextArrow();
        sexSpinner();
        stateSpinner();
        dateOfBirth();
        arrowBack();

    }

    private void arrowBack() {
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(NewUser2.this, NewUser.class);
                startActivity(myIntent);
            }
        });

    }

    private void dateOfBirth() {
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar myCalender = Calendar.getInstance();
                int myYear = myCalender.get(Calendar.YEAR);
                int myMonth = myCalender.get(Calendar.MONTH);
                int myDay = myCalender.get(Calendar.DAY_OF_MONTH);

//                date picker dialog
                myDatePickerDialog = new DatePickerDialog(NewUser2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        //set day of month, month of year value in the edit text
                        dateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },
                        myYear, myMonth, myDay);
                myDatePickerDialog.show();
            }
        });
    }

    private void stateSpinner() {
        stateSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, states);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
        stateSpinner.setAdapter(myArrayAdapter);

    }

    private void nextArrow() {
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(NewUser2.this, NewUser3.class);
                startActivity(myIntent);
            }
        });
    }

    public void sexSpinner(){
        sexSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this,R.layout.list_item, sex);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
        sexSpinner.setAdapter(myArrayAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(), sex[i], Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), states[i], Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), countries[i], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}