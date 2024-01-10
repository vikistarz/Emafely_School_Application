package com.example.emafelyapp.utility;

public class EventsModel {

    private int images;
    private String description;

    public EventsModel(int images, String description) {
        this.images = images;
        this.description = description;
    }


    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
