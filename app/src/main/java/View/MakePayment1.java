package View;

import static android.media.CamcorderProfile.get;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.emafelyapp.R;

import java.util.ArrayList;

import Utility.AppConstant;
import Utility.PaymentAdapter;
import Utility.PaymentModel;
import Utility.RecyclerViewInterface;

public class MakePayment1 extends AppCompatActivity implements RecyclerViewInterface,AdapterView.OnItemSelectedListener {

    private View backArrow;

    private TextView parentFullName;

    private Spinner paymentSpinner;
    private RecyclerView myRecyclerView;

    ArrayList<PaymentModel> myPaymentModel = new ArrayList<>();


    String paymentVia [] = {"MasterCard","Verve","Visa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_payment1);

        inItView();
        inItListener();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.my_preference), Context.MODE_PRIVATE);
        String parentName  = mySharedPreference.getString(AppConstant.parentName, " ");

        parentFullName.setText("Welcome  " + parentName);
    }

    private void inItView() {
        parentFullName = findViewById(R.id.tv_parent_name);
        backArrow = findViewById(R.id.arrow_back);
        paymentSpinner = findViewById(R.id.spinner_make_payment);
        myRecyclerView = findViewById(R.id.payment_recycler_view);

        myRecyclerView();
    }

    private void myRecyclerView() {

        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayoutManager);
        RecyclerView.Adapter myAdapter = new PaymentAdapter(this, myPaymentModel, this);

        myPaymentModel.add(new PaymentModel(false, "School Fees", "16000.00"));
        myPaymentModel.add(new PaymentModel(false, "Uniform", "5000.00"));
        myPaymentModel.add(new PaymentModel(false, "Text Books", "15000.00"));
        myPaymentModel.add(new PaymentModel(false, "Sport Wear", "3000.00"));
        myPaymentModel.add(new PaymentModel(false, "Excursion", "5000.00"));

        myRecyclerView.setAdapter(myAdapter);
    }

    private void inItListener() {
        backArrow();
        paymentSpinner();
    }


    private void backArrow() {

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MakePayment1.this, PayGuide.class);
                startActivity(myIntent);
            }
        });
    }

    private void paymentSpinner() {
        paymentSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this, R.layout.list_item3, paymentVia);
        myArrayAdapter.setDropDownViewResource(R.layout.list_item3);
        paymentSpinner.setAdapter(myArrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void handleClickItem(PaymentModel myPaymentModel) {

        Intent myIntent = new Intent(MakePayment1.this, PaymentDetails.class);
        myIntent.putExtra("fees_detail", myPaymentModel.getFees());
        myIntent.putExtra("amount_charged", myPaymentModel.getAmount());
        startActivity(myIntent);

    }
}