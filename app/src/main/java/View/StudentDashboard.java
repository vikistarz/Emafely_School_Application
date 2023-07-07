package View;

import static android.content.Intent.ACTION_VIEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emafelyapp.R;
import com.google.android.material.navigation.NavigationView;

import Utility.AppConstant;

public class StudentDashboard extends AppCompatActivity {

    DrawerLayout myDrawerLayout;
    NavigationView myNavView;

    Toolbar myToolbar;

    ActionBarDrawerToggle myActionBarDrawerToggle;
    private LinearLayout userProfile, checkResult, payFees, assignment, newsAndEvents, announcement, holiday, faq, aboutApp;
    private TextView profileName;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(myActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);

        inItView();
        inItListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String parentName  = mySharedPreference.getString(AppConstant.parentName, " ");

        profileName.setText("Hi, " + parentName);
    }



    public void inItView() {
        myToolbar = findViewById(R.id.tool_bar);
        myNavView = findViewById(R.id.nav_menu);
        myDrawerLayout = findViewById(R.id.drawer_layout);
        userProfile = findViewById(R.id.layout_user_profile);
        checkResult = findViewById(R.id.layout_check_result);
        announcement = findViewById(R.id.layout_announcement_pta);
        payFees = findViewById(R.id.layout_pay_fees);
        assignment = findViewById(R.id.layout_assignment);
        holiday = findViewById(R.id.layout_holiday);
        faq = findViewById(R.id.layout_faq);
        aboutApp = findViewById(R.id.layout_about_app);
        newsAndEvents = findViewById(R.id.layout_news_and_event);
        profileName = findViewById(R.id.tv_profile_name);

        navigationDrawer();
    }

    private void navigationDrawer() {
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        myActionBarDrawerToggle = new ActionBarDrawerToggle(this,myDrawerLayout, myToolbar, R.string.nav_open, R.string.nav_close);
        myDrawerLayout.addDrawerListener(myActionBarDrawerToggle);
         myActionBarDrawerToggle.syncState();
         myNavView.bringToFront();
        myNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_announcement:
                        Intent announcementIntent = new Intent(StudentDashboard.this, Announcement.class);
                        startActivity(announcementIntent);
                        break;
                    case R.id.nav_retreat:
                        Intent retreatIntent = new Intent(StudentDashboard.this, NewsEvent.class);
                        startActivity(retreatIntent);
                        break;
                    case R.id.nav_admission:
                        Intent admissionIntent = new Intent(StudentDashboard.this, PayGuide.class);
                        startActivity(admissionIntent);
                        break;
                    case R.id.nav_log_out:
                        Intent logoutIntent = new Intent(StudentDashboard.this, MainPage1.class);
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

    public void inItListener() {

        userProfile();
        checkResult();
        payFees();
        assignment();
        announcement();
        holiday();
        faq();
        aboutApp();
        newsAndEvents();
    }

    private void newsAndEvents() {
        newsAndEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StudentDashboard.this, NewsEvent.class);
                startActivity(myIntent);

            }
        });
    }

    private void aboutApp() {
        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void faq() {

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void holiday() {
        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void announcement() {
        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StudentDashboard.this, Announcement.class);
                startActivity(myIntent);


            }
        });
    }

    private void assignment() {
        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    private void payFees() {
        payFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(StudentDashboard.this, PayGuide.class);
                startActivity(myIntent);


            }
        });
    }

    private void checkResult() {
        checkResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StudentDashboard.this, ResultPage.class);
                startActivity(myIntent);

            }
        });
    }

    private void userProfile() {
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(StudentDashboard.this, ProfilePage.class);
                startActivity(myIntent);


            }
        });
    }
}