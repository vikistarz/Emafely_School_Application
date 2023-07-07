package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emafelyapp.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import Utility.AppConstant;

public class NewUser2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText homeAddressEditText, phoneNumberEditText, dateOfBirthEditText;
    private Spinner sexSpinnerLayout, stateSpinnerLayout;
    private View arrowBack;
    private LinearLayout nextArrow;

    private ProgressBar myProgressBar;
    int counter = 0;

    private DatePickerDialog myDatePickerDialog;


    String sex[] = {" ", "Male", "Female"};


    String states[] = {" ", "Abia", "Adamawa", " Akwa Ibom", " Anambra", "Bauchi", " Bayelsa", "Benue", " Borno", "Cross River",
            "Delta", "Ebonyi", "Edo", "Ekiti", "Enugu", "Gombe", "Imo", "Jigawa", "Kaduna", " Kano", " Katsina",
            "Kebbi", "Kogi", "Kwara", "Lagos", "Nasarawa", "Niger", "Ogun", "Ondo", "Osun", "Oyo", "Plateau",
            "Rivers", "Sokoto", "Taraba", "Yobe", "Zamfara", "FCT"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user2);

        inItView();
        inItListener();
    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences mySharedPreferences = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putString(AppConstant.homeAddress, homeAddressEditText.getText().toString());
        myEditor.putString(AppConstant.phoneNumber, phoneNumberEditText.getText().toString().trim());
        myEditor.putString(AppConstant.dateOfBirth, dateOfBirthEditText.getText().toString().trim());
        myEditor.putString(AppConstant.sexSpinner, sexSpinnerLayout.getSelectedItem().toString().trim());
        myEditor.putString(AppConstant.stateSpinner, stateSpinnerLayout.getSelectedItem().toString().trim());
        myEditor.apply();
    }

    public void inItView() {
        homeAddressEditText = findViewById(R.id.et_home_address);
        phoneNumberEditText = findViewById(R.id.et_home_phone);
        dateOfBirthEditText = findViewById(R.id.et_date_of_birth);
        sexSpinnerLayout = findViewById(R.id.spinner_sex);
        stateSpinnerLayout = findViewById(R.id.spinner_state);
        nextArrow = findViewById(R.id.linear_layout_next);
        arrowBack = findViewById(R.id.arrow_back);
        myProgressBar = findViewById(R.id.progress_bar);
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
        dateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
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
                        dateOfBirthEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },
                        myYear, myMonth, myDay);
                myDatePickerDialog.show();
            }
        });
    }

    private void stateSpinner() {
        stateSpinnerLayout.setOnItemSelectedListener(this);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, states);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
        stateSpinnerLayout.setAdapter(myArrayAdapter);

    }

    private void nextArrow() {
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myProgressBar.setVisibility(View.VISIBLE);

                Timer myTimer = new Timer();
                TimerTask myTimerTask = new TimerTask() {
                    @Override
                    public void run() {

                        counter++;

                        myProgressBar.setProgress(counter);

                        if (counter == 30) {
                            myTimer.cancel();
                        }

                    }
                };
                myTimer.schedule(myTimerTask, 30, 30);
                if (validatePhoneNumber() & validateDateOfBirth() & validateSexSpinner() & validateStateSpinner()) {
                    Intent myIntent = new Intent(NewUser2.this, NewUser3.class);
                    startActivity(myIntent);
                }
            }
        });
    }

    public void sexSpinner() {
        sexSpinnerLayout.setOnItemSelectedListener(this);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, sex);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
        sexSpinnerLayout.setAdapter(myArrayAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private boolean validatePhoneNumber() {
        String retrievalPhoneNumber = phoneNumberEditText.getText().toString().trim();
        String acceptableNumbers = "^([0-9]*)$";

        if (retrievalPhoneNumber.isEmpty()) {
            phoneNumberEditText.setError("Can't be empty");
            return false;
        } else if (retrievalPhoneNumber.length() < 11) {
            phoneNumberEditText.setError("Number typed invalid");
            return false;
        }
        else if (retrievalPhoneNumber.length() > 11) {
            phoneNumberEditText.setError("Number typed invalid");
            return false;
        }
        else if (!retrievalPhoneNumber.matches(acceptableNumbers)) {
            phoneNumberEditText.setError("must only be numbers");
            return false;
        }
        return true;
    }

//    private boolean validateHomeAddress() {
//        String retrievalHomeAddress = homeAddressEditText.getText().toString();
//        String acceptableHomeAddress = "^([a-zA-Z][a-zA-Z$+.,_&\\d]*)$";
//
//
//        if (retrievalHomeAddress.isEmpty()) {
//            homeAddressEditText.setError("Can't be empty");
//            return false;
//        } else if (!retrievalHomeAddress.matches(acceptableHomeAddress)) {
//            homeAddressEditText.setError("start with N0 eg 1 then address");
//            return false;
//        }
//        return true;
//    }

    private boolean validateDateOfBirth() {
        String retrievalDateOfBirth = dateOfBirthEditText.getText().toString().trim();

        if (retrievalDateOfBirth.isEmpty()) {
            dateOfBirthEditText.setError("Can't be empty");
            return false;
        }
        return true;
    }

    private boolean validateSexSpinner() {
        View selectedView = sexSpinnerLayout.getSelectedView();

        if (selectedView != null && selectedView instanceof TextView) {
            TextView selectedTextView = (TextView) selectedView;
            if (selectedTextView.getText().equals(" ")) {
                selectedTextView.setError("error");
                return false;
            }
        }
        return true;
    }

    private boolean validateStateSpinner() {
        View selectedView = stateSpinnerLayout.getSelectedView();

        if (selectedView != null && selectedView instanceof TextView) {
            TextView selectedTextView = (TextView) selectedView;
            if (selectedTextView.getText().equals(" ")) {
                selectedTextView.setError("error");
                return false;
            }
        }
        return true;
    }

}

