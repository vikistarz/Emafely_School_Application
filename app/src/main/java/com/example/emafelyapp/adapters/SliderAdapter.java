package com.example.emafelyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.emafelyapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

import com.example.emafelyapp.utility.SliderData;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

      private Context myContext;
      private ArrayList<SliderData> mySliderData;

    public SliderAdapter(Context myContext, ArrayList<SliderData> mySliderData) {
        this.myContext = myContext;
        this.mySliderData = mySliderData;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.slider_layout, parent, false);
         return new SliderAdapterViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
           SliderData mySliderList = mySliderData.get(position);
           viewHolder.myImage.setImageResource(mySliderList.getImages());
    }

    @Override
    public int getCount() {
        return mySliderData.size();
    }

    public class SliderAdapterViewHolder extends ViewHolder {

        private ImageView myImage;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);

            myImage = itemView.findViewById(R.id.my_image);
        }
    }
}
