package View;

import static Utility.AppConstant.confirmPassword;
import static Utility.AppConstant.password;
import static Utility.AppConstant.username;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.emafelyapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

import Utility.AppConstant;

public class ExistingUser extends AppCompatActivity {
    private EditText usernameEditText;

    private View arrowBack;
    private TextInputEditText passwordEditText;
    private ProgressBar myProgressBar;
    private Button signInButton;
    private TextView forgetPassword, signUp;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.existing_user);

        inItView();
        inItListener();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String loginUsername = mySharedPreference.getString(AppConstant.username, " ");
        String loginPassword = mySharedPreference.getString(AppConstant.password, " ");

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
                Intent myIntent = new Intent(ExistingUser.this, MainPage2.class);
                startActivity(myIntent);
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

//                Thread myThread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        myProgressBar.setVisibility(View.GONE);
//
//                        //here
//                        Intent myIntent = new Intent(ExistingUser.this, StudentDashboard.class);
//                        startActivity(myIntent);
//
//                    }
//
//                });
//                myThread.start();

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

                if(validateUsername() && validatePassword()) {
                    Intent myIntent = new Intent(ExistingUser.this, StudentDashboard.class);
                    startActivity(myIntent);
                }
            }
        });
    }


    private boolean validateUsername() {
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String loginUsername = mySharedPreference.getString(AppConstant.username, " ");
        String retrievalUsername = usernameEditText.getText().toString().trim();


        if (retrievalUsername.isEmpty()) {
            usernameEditText.setError("Can't be empty");
            return false;
        }
        else if (!retrievalUsername.equals(loginUsername)) {
               usernameEditText.setError("Invalid Username");
               return false;
        }
        return true;
    }

    private boolean validatePassword() {
        String retrievalPassword = passwordEditText.getText().toString().trim();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String loginPassword = mySharedPreference.getString(AppConstant.password, " ");


        if (retrievalPassword.isEmpty()) {
            passwordEditText.setError("Can't be empty");
            return false;
        } else if (!retrievalPassword.equals(loginPassword)) {
            passwordEditText.setError("Invalid Password");
            return false;
        }
        return true;
    }
}