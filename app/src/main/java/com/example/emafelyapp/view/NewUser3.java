package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emafelyapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

import com.example.emafelyapp.utility.AppConstant;

public class NewUser3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText cityEditText, passwordEditText, confirmPasswordEdittext;

    private View arrowBack;

    private Spinner relationShipSpinnerLayout;
    private CheckBox myCheckBox;

    private ProgressBar myProgressBar;
    String city, password, confirmPassword;



    int counter = 0;
    private Button submitButton;

    String relationShipToChild[] = {"Relationship to Child"," ", "Father", "Mother", "Uncle", "Aunt", "Grand Father", "Grand Mother", "cousin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user3);

        inItView();
        inItListener();
        textListener();
    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences mySharedPreferences = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putString(AppConstant.city, cityEditText.getText().toString().trim());
        myEditor.putString(AppConstant.relationshipToChildSpinner, relationShipSpinnerLayout.getSelectedItem().toString().trim());
        myEditor.putString(AppConstant.password, passwordEditText.getText().toString().trim());
        myEditor.putString(AppConstant.confirmPassword, confirmPasswordEdittext.getText().toString().trim());
        myEditor.apply();

    }


    public void inItView() {
        arrowBack = findViewById(R.id.arrow_back);
        cityEditText = findViewById(R.id.et_city);
        passwordEditText = findViewById(R.id.et_password);
        confirmPasswordEdittext = findViewById(R.id.et_confirm_password);
        myCheckBox = findViewById(R.id.check_box);
        relationShipSpinnerLayout = findViewById(R.id.spinner_relationship);
        submitButton = findViewById(R.id.btn_submit_details);
        myProgressBar = findViewById(R.id.progress_bar);


    }

    public void inItListener() {

        submitButton();
        relationShipSpinner();
        arrowBack();


    }


    private void relationShipSpinner() {

        ArrayAdapter myArrayAdapter = new ArrayAdapter(this, R.layout.list_item, relationShipToChild);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item_2);
        relationShipSpinnerLayout.setAdapter(myArrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(), relationShipToChild[i], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        if (relationShipSpinnerLayout.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "select item", Toast.LENGTH_SHORT).show();
        }
    }

    private void arrowBack() {
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               NewUser3.super.onBackPressed();
            }
        });
    }


    private void submitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!relationShipSpinnerLayout.isSelected() &&
                        myCheckBox.isChecked() &&
                        cityEditText.length() > 0 &&
                        passwordEditText.length() > 0 &&
                        confirmPasswordEdittext.length() > 0) {
                    validatePassword(password);
                    validateConfirmPassword(confirmPassword);
                    validateCity(city);
                    validateRelationshipToChildSpinner();
                    validateCheckbox();



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
                    Intent myIntent = new Intent(NewUser3.this, ExistingUser.class);
                    startActivity(myIntent);
                }

            }

        });
    }

    private void textListener() {
        cityListener();
        passwordListener();
        confirmPasswordListener();
    }

    private void cityListener() {
        cityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                city= s.toString();
                validateCity(city);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                city= s.toString();
                validateCity(city);

            }
        });
    }


    private void passwordListener() {
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                password = s.toString().trim();
                validatePassword(password);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString().trim();
                validatePassword(password);
            }
        });

    }

    private void confirmPasswordListener() {
        confirmPasswordEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                confirmPassword = s.toString().trim();
                validateConfirmPassword(confirmPassword);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmPassword = s.toString().trim();
                validateConfirmPassword(confirmPassword);
            }
        });

    }
    /**
     * Handles city validation
     **/
    private void validateCity(String city) {
        boolean isValid = true;

        // Check length
        if (city.length() < 2) {
            isValid = false;
        }

        // Check alphabetic characters
        if (!city.matches("^[a-zA-Z]*$")) {
            isValid = false;
        }

        if (isValid) {
            // Name is valid
            cityEditText.setError(null);
        }
        else{
            // Name is invalid
            cityEditText.setError("Please enter a valid City name.");
        }
    }



    /**
     * Handles Password validation
     **/
    private void validatePassword(String password) {
        boolean isValid = true;

        // Check length
        if (password.length() < 8) {
            isValid = false;
        }

        // Check uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            isValid = false;
        }

        // Check lowercase letter
        if (!password.matches(".*[a-z].*")) {
            isValid = false;
        }

        // Check digit
        if (!password.matches(".*\\d.*")) {
            isValid = false;
        }

        if (isValid) {
           passwordEditText.setError(null);
            // Password is valid
        } else {
            // Password is invalid
           passwordEditText.setError("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit.");
        }
    }

    /**
     * Handles Confirm Password validation
     **/
    private void validateConfirmPassword(String confirmPassword) {

        if (confirmPassword.equals(password)) {
            confirmPasswordEdittext.setError(null);

        } else {
            confirmPasswordEdittext.setError("Password does not match");
        }
    }

//    private boolean validateCity() {
//        String retrievalCity = cityEditText.getText().toString().trim();
//        String acceptableAlphabets = "^([a-zA-Z]*)$";
//
//        if (retrievalCity.isEmpty()) {
//            cityEditText.setError("Can't be empty");
//            return false;
//        } else if (!retrievalCity.matches(acceptableAlphabets)) {
//            cityEditText.setError("Must only be in alphabets");
//            return false;
//        }
//        return true;
//    }
//
//    private boolean validatePasswordAndConfirmPassword() {
//
//        String retrievalPassword = passwordEditText.getText().toString().trim();
//        String retrievalConfirmPassword = confirmPasswordEdittext.getText().toString().trim();
//        String acceptablePassword = "^([a-zA-Z@#?$+._\\d]*)$";
//
//        if (retrievalPassword.isEmpty()) {
//            passwordEditText.setError("Can't be empty");
//            return false;
//        } else if (retrievalPassword.length() < 6) {
//            passwordEditText.setError("password too short");
//            return false;
//        } else if (!retrievalPassword.matches(acceptablePassword)) {
//            passwordEditText.setError("too weak, requires letter, number or symbol");
//            return false;
//        }
//        else if (retrievalConfirmPassword.isEmpty()) {
//            confirmPasswordEdittext.setError("Can't be empty");
//            return false;
//        }
//
//        else if(!retrievalConfirmPassword.equals(retrievalPassword)) {
//            confirmPasswordEdittext.setError("Password do not match");
//            return false;
//        }
//        return true;
//    }

    private boolean validateRelationshipToChildSpinner() {
        View selectedView = relationShipSpinnerLayout.getSelectedView();

        if (selectedView != null && selectedView instanceof TextView) {
            TextView selectedTextView = (TextView) selectedView;
            if (selectedTextView.getText().equals(" ")) {
                selectedTextView.setError("error");
                return false;
            }
        }
        return true;
    }
    public boolean validateCheckbox(){
        if(!myCheckBox.isChecked()){
            Toast.makeText(this, "tick before you Submit", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
