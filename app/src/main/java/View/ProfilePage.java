package View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emafelyapp.R;

public class ProfilePage extends AppCompatActivity {

    private View backArrow;
    private Button accountInformationButton, languagesButton, notificationButton, contactButton, privacySecurityButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        inItView();
        inItListener();
    }

    public void inItView() {
         accountInformationButton = findViewById(R.id.btn_account_info);
        languagesButton = findViewById(R.id.btn_languages);
        notificationButton = findViewById(R.id.btn_notification);
        contactButton = findViewById(R.id.btn_contact);
        privacySecurityButton = findViewById(R.id.btn_privacy_security);
        logoutButton = findViewById(R.id.btn_log_out);
        backArrow = findViewById(R.id.arrow_back);
    }

    public void inItListener() {

       accountInformationButton();
       backArrow();
       logoutButton();
    }

    private void logoutButton() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfilePage.this, MainPage1.class);
                startActivity(myIntent);
            }
        });
    }

    private void accountInformationButton() {
        accountInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfilePage.this, AccountInformation.class);
                startActivity(myIntent);
            }
        });
    }

    private void backArrow() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfilePage.this, StudentDashboard.class);
                startActivity(myIntent);
            }
        });
    }

}