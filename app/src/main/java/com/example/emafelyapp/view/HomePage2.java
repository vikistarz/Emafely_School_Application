package com.example.emafelyapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emafelyapp.R;
import com.example.emafelyapp.adapters.SliderAdapter;
import com.example.emafelyapp.utility.SliderData;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomePage2 extends AppCompatActivity {

    private Button getStartedButton;
    SliderView mySlider;
    private View arrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page2);

        inItView();
        inItListener();
    }

    public void inItView() {
        getStartedButton = findViewById(R.id.btn_get_started);
//        arrowBack = findViewById(R.id.arrow_back);
        mySlider = findViewById(R.id.slider);
        sliderView();
    }


    public void inItListener() {
        getStartedButton();
//        arrowBack();

    }

    private void sliderView() {
        ArrayList<SliderData> mySliderData = new ArrayList<>();
        mySliderData.add(new SliderData(R.drawable.group_nineteen));
        mySliderData.add(new SliderData(R.drawable.group_nineteen));

        SliderAdapter mySliderAdapter = new SliderAdapter(this, mySliderData);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        mySlider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setAdapter to mySlider
        mySlider.setSliderAdapter(mySliderAdapter);


        // below method is use to set
        // scroll time in seconds.
        mySlider.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        mySlider.setAutoCycle(true);

        // to start autoCycle below method is used.
        mySlider.startAutoCycle();

    }
//    private void arrowBack() {
//        arrowBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(HomePage2.this, SplashScreenActivity.class);
//                startActivity(myIntent);
//            }
//        });
//    }

    private void getStartedButton() {
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomePage2.this, MainPage1.class);
                startActivity(myIntent);
            }
        });
    }
}