package com.biletus.app;

import java.io.Serializable;

public class EventModel implements Serializable {
    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String eventPrice;
    private int imageResourceId;

    public EventModel(String eventName, String eventDate, String eventLocation, String eventPrice, int imageResourceId) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.imageResourceId = imageResourceId;
    }

    public String getEventName() { return eventName; }
    public String getEventDate() { return eventDate; }
    public String getEventLocation() { return eventLocation; }
    public String getEventPrice() { return eventPrice; }
    public int getImageResourceId() { return imageResourceId; }
}