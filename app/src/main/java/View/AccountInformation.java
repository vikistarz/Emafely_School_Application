package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emafelyapp.R;

import Utility.AppConstant;

public class AccountInformation extends AppCompatActivity {

    private TextView cancel, done;
    private ImageView picture;
    private EditText parentNameEditText, childNameEditText, phoneNumberEditText, usernameEditText, emailAddressEditText, homeAddressEditText;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_information);

        inItView();
        inItListener();
    }

    public void inItView() {
       parentNameEditText = findViewById(R.id.et_parent_name);
        usernameEditText = findViewById(R.id.et_username);
        emailAddressEditText = findViewById(R.id.et_email_address);
       phoneNumberEditText = findViewById(R.id.et_phone_number);
        childNameEditText = findViewById(R.id.et_child_name);
        homeAddressEditText = findViewById(R.id.et_home_address);
        logoutButton = findViewById(R.id.btn_log_out);
        cancel = findViewById(R.id.tv_cancel);
        done= findViewById(R.id.tv_done);
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences mySharedPreferences = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String parentName = mySharedPreferences.getString(AppConstant.parentName, "");
        String childName = mySharedPreferences.getString(AppConstant.childName, "");
        String username = mySharedPreferences.getString(AppConstant.username, "");
        String emailAddress = mySharedPreferences.getString(AppConstant.emailAddress, "");
        String homeAddress = mySharedPreferences.getString(AppConstant.homeAddress, "");
        String phoneNumber = mySharedPreferences.getString(AppConstant.phoneNumber, "");

        parentNameEditText.setText(parentName);
        childNameEditText.setText(childName);
        usernameEditText.setText(username);
        emailAddressEditText.setText(emailAddress);
        homeAddressEditText.setText(homeAddress);
        phoneNumberEditText.setText(phoneNumber);

    }

        public void inItListener() {
      logoutButton();
        cancel();

    }

    private void logoutButton() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AccountInformation.this, MainPage1.class);
                startActivity(myIntent);
            }
        });
    }

    private void cancel() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AccountInformation.this, ProfilePage.class);
                startActivity(myIntent);
            }
        });
    }



}