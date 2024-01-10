package com.example.emafelyapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emafelyapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    private Button getStartedButton;
    private TextView firstLine, secondLine, thirdLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        inItView();
    }

    public void inItView() {
        getStartedButton = findViewById(R.id.btn_get_started);
        firstLine = findViewById(R.id.tv_first_line);
        secondLine = findViewById(R.id.tv_second_line);
        thirdLine = findViewById(R.id.tv_third_line);

        splashScreen();
    }

    private void splashScreen() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.side_slide);
        firstLine.startAnimation(animation);
        secondLine.startAnimation(animation);
        thirdLine.startAnimation(animation);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //This method will be executed once the timer is over
//                //Start your app main activity
//                Intent intent = new Intent(HomePage1.this, HomePage2.class);
//                startActivity(intent);
//                //close this activity
//                finish();
//            }
//        }, 4000);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            try {
                Intent intent = new Intent(SplashScreenActivity.this, HomePage2.class);
                startActivity(intent);
                SplashScreenActivity.this.finishAffinity();
            } catch (NullPointerException npe) {
                // Handle or log the exception
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}