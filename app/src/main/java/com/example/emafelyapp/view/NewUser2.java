package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.emafelyapp.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.example.emafelyapp.utility.AppConstant;

public class NewUser2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText homeAddressEditText, phoneNumberEditText, dateOfBirthEditText;
    private Spinner sexSpinnerLayout, stateSpinnerLayout;
    private View arrowBack;
    private LinearLayout nextArrow;
    String  dateOfBirth, homePhone, homeAddress;

    TextView genderTv;

    private ProgressBar myProgressBar;
    int counter = 0;

    private DatePickerDialog myDatePickerDialog;


    String gender[] = {"Gender", " ", "Male", "Female"};


    String states[] = {"State Of Origin"," ", "Abia", "Adamawa", "Akwa Ibom", "Anambra", "Bauchi", "Bayelsa", "Benue", "Borno", "Cross River",
            "Delta", "Ebonyi", "Edo", "Ekiti", "Enugu", "Gombe", "Imo", "Jigawa", "Kaduna", "Kano", "Katsina",
            "Kebbi", "Kogi", "Kwara", "Lagos", "Nasarawa", "Niger", "Ogun", "Ondo", "Osun", "Oyo", "Plateau",
            "Rivers", "Sokoto", "Taraba", "Yobe", "Zamfara", "FCT"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user2);

        inItView();
        inItListener();
        textListener();
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
        sexSpinnerLayout = findViewById(R.id.spinner_gender);
        stateSpinnerLayout = findViewById(R.id.spinner_state);
        nextArrow = findViewById(R.id.linear_layout_next);
        arrowBack = findViewById(R.id.arrow_back);
        myProgressBar = findViewById(R.id.progress_bar);
//        genderTv = findViewById(R.id.tv_gender);
//
//        genderTv.setVisibility(View.VISIBLE);
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
             NewUser2.super.onBackPressed();

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

        ArrayAdapter myArrayAdapter = new ArrayAdapter(this, R.layout.list_item, states);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
        stateSpinnerLayout.setAdapter(myArrayAdapter);
    }

    private void nextArrow() {
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if     (!sexSpinnerLayout.isSelected()&&
                        !stateSpinnerLayout.isSelected() &&
                        dateOfBirthEditText.length() > 0 &&
                        phoneNumberEditText.length() > 0 &&
                        homeAddressEditText.length() > 0){
                    validateSexSpinner();
                    validateDateOfBirth();
                    validatePhoneNumber(homePhone);
                    validateHomeAddress(homeAddress);
                    validateStateSpinner();



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
                    myProgressBar.setVisibility(View.VISIBLE);
                    Intent myIntent = new Intent(NewUser2.this, NewUser3.class);
                    startActivity(myIntent);
                }
            }
        });
    }

    public void sexSpinner(){

                ArrayAdapter myArrayAdapter = new ArrayAdapter(this, R.layout.list_item, gender);
                myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
                sexSpinnerLayout.setAdapter(myArrayAdapter);
            }

              @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

           }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }




    private void textListener() {
        homePhoneListener();
        homeAddressListener();
    }

    private void homePhoneListener() {
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                homePhone = s.toString().trim();
                validatePhoneNumber(homePhone);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                homePhone = s.toString().trim();
                validatePhoneNumber(homePhone);

            }
        });
    }

    private void homeAddressListener() {
        homeAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                homeAddress = s.toString();
                validatePhoneNumber(homeAddress);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                homeAddress = s.toString();
                validatePhoneNumber(homeAddress);

            }
        });
    }


    /**
     * Handles Confirm phoneNumber validation
     **/
    private void validatePhoneNumber(String homePhone) {
        boolean isValid = homePhone.matches("\\d{11}");

        if (isValid) {
            // Phone number is valid
            phoneNumberEditText.setError(null);
        }
        else {
            // Phone number is invalid
            phoneNumberEditText.setError("Please enter a valid 11-digit phone number");
            return;
        }
    }

    /**
     * Handles home address validation
     **/
    private void validateHomeAddress(String homeAddress) {
        boolean isValid = true;

        // Check length
        if (homeAddress.length() < 2) {
            isValid = false;
        }

        // Check alphabetic characters
        if (!homeAddress.matches("^([a-zA-Z][a-zA-Z$+._\\\\d]*)$")) {
            isValid = false;
        }

        if (isValid) {
            // Name is valid
            homeAddressEditText.setError(null);

        }
        else{
            // Name is invalid
            homeAddressEditText.setError("Please enter a valid Address.");
        }
    }





    private boolean validateDateOfBirth() {
        dateOfBirth = dateOfBirthEditText.getText().toString().trim();

        if (dateOfBirth.isEmpty()) {
            dateOfBirthEditText.setError("Can't be empty");
            return false;
        }
        return true;
    }

    private boolean validateSexSpinner() {
        View mySelectedView = sexSpinnerLayout.getSelectedView();

        if (mySelectedView != null && mySelectedView instanceof TextView) {
            TextView selectedTextView = (TextView) mySelectedView;
            if (selectedTextView.getText().equals(" ")) {
                selectedTextView.setError("can't be empty");
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
                selectedTextView.setError("can't be empty");
                return false;
            }
        }
        return true;
    }

}

