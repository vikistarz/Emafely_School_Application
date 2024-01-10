package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.emafelyapp.R;

import java.util.Timer;
import java.util.TimerTask;

import com.example.emafelyapp.utility.AppConstant;

public class ExistingUser extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;

    private View arrowBack;
    private ProgressBar myProgressBar;
    private Button signInButton;
    private TextView forgetPassword, signUp;
    SharedPreferences mySharedPreference;

    String userName, password;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.existing_user);

        inItView();
        inItListener();
        textListener();

    }

    protected void onResume() {
        super.onResume();

        mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String loginUsername = mySharedPreference.getString(AppConstant.username, " ");


        usernameEditText.setText(loginUsername);

    }

    public void inItView() {
        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);
        signInButton = findViewById(R.id.btn_sign_in);
        myProgressBar = findViewById(R.id.progress_bar);
        forgetPassword = findViewById(R.id.tv_forget_password);
        signUp = findViewById(R.id.tv_sign_up);
        arrowBack = findViewById(R.id.arrow_back);


    }

    public void inItListener() {
        arrowBack();
        signInButton();
        signUp();
        forgetPassword();


    }


    private void arrowBack() {
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ExistingUser.super.onBackPressed();
            }
        });
    }

    private void forgetPassword() {
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ExistingUser.this, ForgetPassword.class);
                startActivity(myIntent);
            }
        });

    }

    private void signUp() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ExistingUser.this, NewUser.class);
                startActivity(myIntent);
            }
        });

    }

    private void signInButton() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usernameEditText.length() > 0 &&
                        passwordEditText.length() > 0 && validateUsername(userName) &&  validatePassword(password)) {


              myProgressBar.setVisibility(View.VISIBLE);

              Timer myTimer = new Timer();
                TimerTask myTimerTask = new TimerTask() {
                    @Override
                    public void run() {

                        counter++;
                        myProgressBar.setProgress(counter);

                        if(counter == 30){
                            myTimer.cancel();

                        }
                    }
                };
                  myTimer.schedule(myTimerTask, 30, 30);


                    Intent myIntent = new Intent(ExistingUser.this, StudentDashboard.class);
                    startActivity(myIntent);
                }
            }
        });
    }

    private void textListener() {
       usernameListener();
        passwordListener();

    }

    private void usernameListener() {
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userName = s.toString().trim();
                validateUsername(userName);
                signInButton.setEnabled(!userName.isEmpty());
            }
        });
    }

    private void passwordListener() {
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString().trim();
                validatePassword(password);
                signInButton.setEnabled(!password.isEmpty());
            }
        });

    }

    /**
     * Handles Password validation
     **/
//    private void validatePassword(String password) {
//        mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
//       String loginPassword = mySharedPreference.getString(AppConstant.password, " ");
//        boolean isValid = true;
//
//        if (isValid) {
//            passwordEditText.setError(null);
//            // Password is valid
//        }
//         if (!password.equals(loginPassword)) {
//           passwordEditText.setError("Invalid Password");
//        }
//
//    }

    /**
     * Handles username validation
     *
     * @return
     */
//    private boolean validateUsername(String userName) {
//        mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
//       String loginUsername = mySharedPreference.getString(AppConstant.username, " ");
//        boolean isValid = true;
//
//
//        if (isValid) {
//            // Name is valid
//            usernameEditText.setError(null);
//
//        }
//
//        if (!userName.equals(loginUsername)) {
//               usernameEditText.setError("Invalid Username");
//        }
//        return true;
//    }

    private boolean validateUsername(String userName) {
        mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String loginUsername = mySharedPreference.getString(AppConstant.username, " ");


        if (userName.isEmpty()) {
            usernameEditText.setError("Can't be empty");
            return false;
        } else if (!userName.equals(loginUsername)) {
            passwordEditText.setError("Invalid Password");
            return false;
        }
        return true;
    }


    private boolean validatePassword(String password) {
         mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String loginPassword = mySharedPreference.getString(AppConstant.password, " ");


        if (password.isEmpty()) {
            passwordEditText.setError("Can't be empty");
            return false;
        } else if (!password.equals(loginPassword)) {
            passwordEditText.setError("Invalid Password");
            return false;
        }
        return true;
    }



}