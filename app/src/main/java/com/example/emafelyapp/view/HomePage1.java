package com.example.emafelyapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.emafelyapp.R;

public class HomePage1 extends AppCompatActivity {

    private Button getStartedButton;
    private TextView firstLine, secondLine, thirdLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page1);

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                //Start your app main activity
                Intent intent = new Intent(HomePage1.this, HomePage2.class);
                startActivity(intent);
                //close this activity
                finish();
            }
        }, 4000);
    }

}