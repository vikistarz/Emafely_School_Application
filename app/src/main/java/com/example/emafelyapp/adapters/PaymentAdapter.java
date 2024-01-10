package com.example.emafelyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emafelyapp.utility.PaymentModel;
import com.example.emafelyapp.utility.RecyclerViewInterface;

import com.example.emafelyapp.R;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>{

  private final Context myContext;
   private  final ArrayList<PaymentModel> myPaymentModel;
   private final RecyclerViewInterface myRecyclerViewInterface;


    public PaymentAdapter(Context myContext, ArrayList<PaymentModel> myPaymentModel, RecyclerViewInterface myRecyclerViewInterface) {
        this.myContext = myContext;
        this.myPaymentModel = myPaymentModel;
        this.myRecyclerViewInterface = (RecyclerViewInterface) myContext;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.payment_layout, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
        PaymentModel myPaymentList = myPaymentModel.get(position);
        holder.myCheckbox.setChecked(myPaymentList.isCheckbox());
        holder.myFees.setText(myPaymentList.getFees());
        holder.myAmount.setText(myPaymentList.getAmount());

        holder.myCheckbox.setOnClickListener(view ->
                myRecyclerViewInterface.handleClickItem(myPaymentModel.get(position)));

    }

    @Override
    public int getItemCount() {
        return myPaymentModel.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CheckBox myCheckbox;
        private final TextView myFees, myAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myCheckbox = itemView.findViewById(R.id.check_box);
            myFees = itemView.findViewById(R.id.tv_fees);
            myAmount = itemView.findViewById(R.id.tv_amount);

            myCheckbox.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
      // now you can catch events on click

            myRecyclerViewInterface.handleClickItem(myPaymentModel.get(getAdapterPosition()));


        }
    }

    public ArrayList<PaymentModel> getAll(){
        return myPaymentModel;
    }

    public ArrayList<PaymentModel> getSelected(){

         ArrayList<PaymentModel> selected = new ArrayList<>();
        for(int i = 0; i < myPaymentModel.size(); i ++){
            if(myPaymentModel.get(i).isCheckbox()){
                selected.add(myPaymentModel.get(i));
            }
        }
        return selected;
    }

}
