package com.example.emafelyapp.view;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emafelyapp.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Date;

import com.example.emafelyapp.adapters.EventsAdapter;
import com.example.emafelyapp.adapters.SliderAdapter;
import com.example.emafelyapp.utility.EventRecyclerInterface;
import com.example.emafelyapp.utility.EventsModel;
import com.example.emafelyapp.utility.SliderData;

public class NewsEvent extends AppCompatActivity implements EventRecyclerInterface {

    private View arrowBack;

    private TextView date, time;

    RecyclerView myRecyclerView1, myRecyclerView2;

    RecyclerView.LayoutManager myLayoutManager;
    RecyclerView.Adapter myAdapter;

  SliderView mySlider;

    ArrayList<EventsModel> myEventsModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_event);

        inItView();
        inItListener();

    }



    private void inItView() {
        arrowBack = findViewById(R.id.arrow_back);
        date = findViewById(R.id.tv_current_date);
        time = findViewById(R.id.tv_current_time);
        myRecyclerView1 = findViewById(R.id.recycler_view1);
        myRecyclerView2 = findViewById(R.id.recycler_view2);
        mySlider = findViewById(R.id.slider);

        myRecyclerView1();
        myRecyclerView2();
        sliderView();
        dateFormat();
    }

    private void dateFormat() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy G");
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:MM:ss Z");

        String currentDate = sdfDate.format(new Date());
        String currentTime = sdfTime.format(new Date());

        date.setText(currentDate);
        time.setText(currentTime);
    }

    private void sliderView() {

        ArrayList<SliderData> mySliderData = new ArrayList<>();
        mySliderData.add(new SliderData(R.drawable.news_image));
        mySliderData.add(new SliderData(R.drawable.news_image));

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


    private void myRecyclerView1() {
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRecyclerView1.setHasFixedSize(true);
        myRecyclerView1.setLayoutManager(myLayoutManager);
        myAdapter = new EventsAdapter(this, myEventsModel, this);

        myEventsModel = new ArrayList<>();
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myRecyclerView1.setAdapter(myAdapter);
    }

    private void myRecyclerView2() {
        myLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRecyclerView2.setHasFixedSize(true);
        myRecyclerView2.setLayoutManager(myLayoutManager);
        myAdapter = new EventsAdapter(this, myEventsModel, this);

        myEventsModel = new ArrayList<>();
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myEventsModel.add(new EventsModel(R.drawable.white_native, "Private school operators and burden of multiple charges"));
        myEventsModel.add(new EventsModel(R.drawable.messi, "Messi’s move to Saudi a ‘done deal’"));
        myEventsModel.add(new EventsModel(R.drawable.police, "Kano: Police arrest 27 suspected criminals"));
        myRecyclerView2.setAdapter(myAdapter);
    }

    private void inItListener() {
        arrowBack();
    }

    private void arrowBack() {
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               NewsEvent.super.onBackPressed();
            }
        });
    }

    @Override
    public void handleEventClick(EventsModel myEventModel) {
        
    }
}