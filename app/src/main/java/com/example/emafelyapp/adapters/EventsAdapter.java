package com.example.emafelyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emafelyapp.R;

import java.util.ArrayList;

import com.example.emafelyapp.utility.EventRecyclerInterface;
import com.example.emafelyapp.utility.EventsModel;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{

      private Context myContext;
    private ArrayList<EventsModel> myEventsModel;
    private EventRecyclerInterface myEventInterface;

    public EventsAdapter(Context myContext, ArrayList<EventsModel> myEventsModel, EventRecyclerInterface myEventInterface) {
        this.myContext = myContext;
        this.myEventsModel = myEventsModel;
        this.myEventInterface = (EventRecyclerInterface) myContext;
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.events_layout, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {
        EventsModel myEventModelList = myEventsModel.get(position);
        holder.myEventImages.setImageResource(myEventModelList.getImages());
        holder.eventDescription.setText(myEventModelList.getDescription());

        holder.itemView.setOnClickListener(View ->
                myEventInterface.handleEventClick(myEventsModel.get(position)));

    }

    @Override
    public int getItemCount() {
        return myEventsModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView myEventImages;
        private TextView eventDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myEventImages = itemView.findViewById(R.id.event_images);
            eventDescription = itemView.findViewById(R.id.tv_event_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myEventInterface.handleEventClick(myEventsModel.get(getAdapterPosition()));
        }
    }
}
