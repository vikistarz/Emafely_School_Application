package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.emafelyapp.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import Utility.EventsAdapter;
import Utility.EventsModel;
import Utility.SliderAdapter;
import Utility.SliderData;

public class NewsEvent extends AppCompatActivity {

    RecyclerView myRecyclerView1, myRecyclerView2;
    RecyclerView.LayoutManager myLayoutManager;
    RecyclerView.Adapter myAdapter, myAdapter2;

  SliderView mySlider;

    ArrayList<EventsModel> myEventsModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_event);

        inItView();

    }


    private void inItView() {
        myRecyclerView1 = findViewById(R.id.recycler_view1);
        myRecyclerView2 = findViewById(R.id.recycler_view2);
        mySlider = findViewById(R.id.slider);

        myRecyclerView1();
        myRecyclerView2();
        sliderView();
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
        myAdapter = new EventsAdapter(this, myEventsModel);

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
        myRecyclerView2.setLayoutManager(myLayoutManager);
        myRecyclerView1.setHasFixedSize(true);
        myAdapter2 = new EventsAdapter(this, myEventsModel);

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
}