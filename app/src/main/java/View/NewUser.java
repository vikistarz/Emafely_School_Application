package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emafelyapp.R;

import java.util.Timer;
import java.util.TimerTask;

import Utility.AppConstant;

public class NewUser extends AppCompatActivity {
    private EditText parentNameEditText, usernameEditText, emailAddressEditText, childNameEditText;
    private View backArrow;

    private ProgressBar myProgressBar;
    private LinearLayout nextArrow;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        inItView();
        inItListener();
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

                if(validateParentName() & validateChildName() & validateEmailAddress() &  validateUsername()){

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
                Intent myIntent = new Intent(NewUser.this, MainPage2.class);
                startActivity(myIntent);
            }
        });
    }
    private boolean validateParentName() {
        String retrievalParentName =parentNameEditText.getText().toString().trim();
        String acceptableAlphabets = "^([a-zA-Z]*)$";

        if (retrievalParentName.isEmpty()) {
            parentNameEditText.setError("Can't be empty");
            return false;
        }
        else if (retrievalParentName.length() < 3) {
            parentNameEditText.setError("Name too short");
            return false;
        } else if (!retrievalParentName.matches(acceptableAlphabets)) {
            parentNameEditText.setError("Must only be in alphabets");
            return false;
        }
        return true;
    }

    private boolean validateChildName() {
        String retrievalChildName = childNameEditText.getText().toString().trim();
        String acceptableAlphabets = "^([a-zA-Z]*)$";

        if (retrievalChildName.isEmpty()) {
            childNameEditText.setError("Can't be empty");
            return false;
        }
        else if (retrievalChildName.length() < 3) {
            childNameEditText.setError("Name too short");
            return false;
        } else if (!retrievalChildName.matches(acceptableAlphabets)) {
            childNameEditText.setError("Must only be in alphabets");
            return false;
        }
        return true;
    }

    private boolean validateEmailAddress() {
        String retrievalEmailAddress = emailAddressEditText.getText().toString().trim();
        String acceptableEmail = "^([a-zA-Z@_.com\\d]*)$";


        if (retrievalEmailAddress.isEmpty()) {
            emailAddressEditText.setError("Can't be empty");
            return false;
        }
        else if (retrievalEmailAddress.length() < 11) {
            emailAddressEditText.setError("Email address too short");
            return false;
        }
        else if (!retrievalEmailAddress.matches(acceptableEmail)) {
            emailAddressEditText.setError("Invalid email");
            return false;
        }
        return true;
    }


    private boolean validateUsername() {
        String retrievalUsername = usernameEditText.getText().toString().trim();
        String alphaNumericNumbers = "^([a-zA-Z][a-zA-Z$+._\\d]*)$";

        if (retrievalUsername.isEmpty()) {
            usernameEditText.setError("Can't be empty");
            return false;
        }
        else if (retrievalUsername.length() < 4) {
            usernameEditText.setError("username too short");
            return false;
        } else if (!retrievalUsername.matches(alphaNumericNumbers)) {
            usernameEditText.setError("username too weak, add number");
            return false;
        }
        return true;
    }

}

