package View;

import static android.content.Intent.ACTION_VIEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emafelyapp.R;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import Utility.SliderAdapter;
import Utility.SliderData;

public class MainPage2 extends AppCompatActivity {

    private View navDrawerCancel;
    private Button enrolNowButton, logInButton;
    private LinearLayout youtubeLayout1, youtubeLayout2, youtubeLayout3;

  SliderView mySlider;
    SliderAdapter mySliderAdapter;
    DrawerLayout myDrawerLayout;
    Toolbar myToolBar;
    NavigationView myNavView;
    ActionBarDrawerToggle myDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(myDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_page2);

        inItView();
        inItListener();
    }

    private void inItView() {
        enrolNowButton = findViewById(R.id.btn_enrol_now);
        logInButton = findViewById(R.id.btn_log_in);
        myDrawerLayout = findViewById(R.id.drawer_layout);
        myToolBar = findViewById(R.id.tool_bar);
        myNavView = findViewById(R.id.nav_view);
        mySlider = findViewById(R.id.slider);
        youtubeLayout1 = findViewById(R.id.layout_youtube1);
        youtubeLayout2 = findViewById(R.id.layout_youtube2);
        youtubeLayout3 = findViewById(R.id.layout_youtube3);
        navDrawerCancel = findViewById(R.id.nav_cancel);

        navigationDrawer();
        sliderView();
    }

    private void sliderView() {

        ArrayList<SliderData> mySliderData = new ArrayList<>();
        mySliderData.add(new SliderData(R.drawable.students_home));
        mySliderData.add(new SliderData(R.drawable.main_student_1));
        mySliderData.add(new SliderData(R.drawable.main_student_2));
        mySliderData.add(new SliderData(R.drawable.main_student_3));

        mySliderAdapter = new SliderAdapter(this, mySliderData);

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

    private void navigationDrawer() {
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        myDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout, myToolBar, R.string.nav_open, R.string.nav_close);
        myDrawerLayout.addDrawerListener(myDrawerToggle);
        myDrawerToggle.syncState();
        myNavView.bringToFront();
        myNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_dashboard:
                        Intent dashboardIntent = new Intent(MainPage2.this, StudentDashboard.class);
                        startActivity(dashboardIntent);
                        break;
                    case R.id.nav_announcement:
                        Intent announcementIntent = new Intent(MainPage2.this, Announcement.class);
                        startActivity(announcementIntent);
                        break;
                    case R.id.nav_retreat:
                        Intent retreatIntent = new Intent(MainPage2.this, NewsEvent.class);
                        startActivity(retreatIntent);
                        break;
                    case R.id.nav_admission:
                        Intent admissionIntent = new Intent(MainPage2.this, PayGuide.class);
                        startActivity(admissionIntent);
                        break;
                    case R.id.nav_log_out:
                        Intent logoutIntent = new Intent(MainPage2.this, MainPage1.class);
                        startActivity(logoutIntent);
                        break;
                    case R.id.nav_contact:
                        Intent contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(contactIntent, 1);
                        break;
                    case R.id.nav_support:
                        Intent supportIntent = new Intent(ACTION_VIEW, Uri.parse("https://www.google.com"));
                        startActivity(supportIntent);
                        break;

                }
                return true;
            }
        });

    }

    private void inItListener() {
        errolNow();
        logIn();
        youtubeLayout();

    }



    private void youtubeLayout() {

        youtubeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                startActivity(myIntent);
            }
        });

        youtubeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                startActivity(myIntent);
            }
        });

        youtubeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                startActivity(myIntent);
            }
        });
    }

    private void errolNow() {
        enrolNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainPage2.this, NewUser.class);
                startActivity(myIntent);
            }
        });
    }

    private void logIn() {
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainPage2.this, ExistingUser.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(myDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            myDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}