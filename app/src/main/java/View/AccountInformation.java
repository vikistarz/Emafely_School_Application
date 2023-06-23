package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emafelyapp.R;

public class AccountInformation extends AppCompatActivity {

    private TextView cancel, done;
    private ImageView picture;
    private EditText firstNameEditText, otherNameEditText, phoneNumberEditText, usernameEditText, emailAddressEditText;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_information);

        inItView();
        inItListener();
    }

    public void inItView() {
       firstNameEditText = findViewById(R.id.et_first_name);
        usernameEditText = findViewById(R.id.et_username);
        emailAddressEditText = findViewById(R.id.et_email_address);
       phoneNumberEditText = findViewById(R.id.et_phone_number);
        otherNameEditText = findViewById(R.id.et_other_name);
        logoutButton = findViewById(R.id.btn_log_out);
        cancel = findViewById(R.id.tv_cancel);
        done= findViewById(R.id.tv_done);
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