package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.emafelyapp.R;

import java.util.Timer;
import java.util.TimerTask;

import com.example.emafelyapp.utility.AppConstant;

public class NewUser extends AppCompatActivity {
    private EditText parentNameEditText, usernameEditText, emailAddressEditText, childNameEditText;
    private View backArrow;

    String parentName, userName, emailAddress, childName;

    private ProgressBar myProgressBar;
    private LinearLayout nextArrow;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        inItView();
        inItListener();
        textListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences mySharedPreferences = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putString(AppConstant.parentName, parentNameEditText.getText().toString());
        myEditor.putString(AppConstant.childName, childNameEditText.getText().toString());
        myEditor.putString(AppConstant.username, usernameEditText.getText().toString().trim());
        myEditor.putString(AppConstant.emailAddress, emailAddressEditText.getText().toString().trim());
        myEditor.apply();
    }

    public void inItView() {
        parentNameEditText = findViewById(R.id.et_parent_name);
        usernameEditText = findViewById(R.id.et_username);
        emailAddressEditText = findViewById(R.id.et_email_address);
        childNameEditText = findViewById(R.id.et_child_name);
        nextArrow = findViewById(R.id.linear_layout_next);
        backArrow = findViewById(R.id.arrow_back);
        myProgressBar = findViewById(R.id.progress_bar);
    }

    public void inItListener() {

        nexArrow();
        backArrow();
    }

    private void nexArrow() {
        nextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (parentNameEditText.length() > 0 &&
                        usernameEditText.length() > 0 &&
                        emailAddressEditText.length() > 0 &&
                        childNameEditText.length() > 0) {
                    validateParentName(parentName);
                    validateUsername(userName);
                    validateEmailAddress(emailAddress);
                    validateChildName(childName);

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

                    Intent myIntent = new Intent(NewUser.this, NewUser2.class);
                    startActivity(myIntent);
                }
            }
        });
    }

    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewUser.super.onBackPressed();

            }
        });
    }


    private void textListener() {
        parentNameListener();
        usernameListener();
        emailListener();
        childNameListener();

    }
    private void parentNameListener() {
        parentNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                parentName =s.toString();
                validateParentName(parentName);
            }
        });
    }

    private void usernameListener() {
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                userName = s.toString().trim();
                validateUsername(userName);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userName = s.toString().trim();
                validateUsername(userName);

            }
        });
    }

    private void emailListener() {
        emailAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                emailAddress = s.toString().trim();
                validateEmailAddress(emailAddress);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                emailAddress = s.toString().trim();
                validateEmailAddress(emailAddress);
            }
        });
    }

    private void childNameListener() {
        childNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                childName = s.toString().trim();
                validateChildName(childName);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                childName = s.toString().trim();
                validateChildName(childName);
            }
        });

    }


    /**
     * Handles parent name validation
     **/
    private void validateParentName(String parentName) {
        boolean isValid = true;

        // Check length
        if (parentName.length() < 2) {
            isValid = false;
        }

        // Check alphabetic characters
        if (!parentName.matches("^[a-zA-Z]*$")) {
            isValid = false;
        }

        if (isValid) {
            // Name is valid
            parentNameEditText.setError(null);

        }
        else{
            // Name is invalid
            parentNameEditText.setError("Please enter a valid name with at least two alphabetic characters.");
        }
    }

    /**
     * Handles username validation
     **/
    private void validateUsername(String userName) {
        boolean isValid = true;

        // Check length
        if (userName.length() < 2) {
            isValid = false;
        }

        // Check alphabetic characters
        if (!userName.matches("^([a-zA-Z][a-zA-Z$+._\\\\d]*)$")) {
            isValid = false;
        }

        if (isValid) {
            // Name is valid
            usernameEditText.setError(null);

        }
        else{
            // Name is invalid
            usernameEditText.setError("Please enter a valid name with at least two alphabetic characters and a number.");
        }
    }

    /**
     * Handles Email validation
     **/
    private void validateEmailAddress(String emailAddress) {
        boolean isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();

        if (emailAddress.length() < 5) {
            isValid = false;
        }

        if (isValid) {
            // Email address is valid
            emailAddressEditText.setError(null);
        }
        else{
            // Email address is invalid
           emailAddressEditText.setError("Please enter a valid email address.");
        }
    }

    /**
     * Handles child Name validation
     **/
    private void validateChildName(String childName) {
        boolean isValid = true;

        // Check length
        if (childName.length() < 2) {
            isValid = false;
        }

        // Check alphabetic characters
        if (!childName.matches("^[a-zA-Z]*$")) {
            isValid = false;
        }

        if (isValid) {
            // Name is valid
            childNameEditText.setError(null);

        }
        else{
            // Name is invalid
            childNameEditText.setError("Please enter a valid name with at least two alphabetic characters.");
        }
    }

}

