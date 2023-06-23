package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.emafelyapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class ExistingUser extends AppCompatActivity {
       private EditText usernameEditText;
       private TextInputEditText passwordEditText;
       private ProgressBar myProgressBar;
        private Button signInButton;
        private TextView forgetPassword, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.existing_user);

        inItView();
        inItListener();
    }
    public void inItView() {
        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);
        signInButton = findViewById(R.id.btn_sign_in);
        myProgressBar = findViewById(R.id.progress_bar);
        forgetPassword = findViewById(R.id.tv_forget_password);
        signUp = findViewById(R.id.tv_sign_up);
    }
    public void inItListener() {

        signInButton();
        signUp();

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

                Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(4000);
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        myProgressBar.setVisibility(View.GONE);
                        Intent myIntent = new Intent(ExistingUser.this, StudentDashboard.class);
                        startActivity(myIntent);
                    }
                });
               myThread.start();
            }
        });
    }


}