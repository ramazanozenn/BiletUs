package com.biletus.app;

import java.io.Serializable;

public class EventModel implements Serializable {
    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String eventPrice; // YENİ EKLENDİ
    private int imageResourceId;

    // Constructor (Yapıcı Metot) - Artık 5 parametre alıyor
    public EventModel(String eventName, String eventDate, String eventLocation, String eventPrice, int imageResourceId) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice; // YENİ
        this.imageResourceId = imageResourceId;
    }

    public String getEventName() { return eventName; }
    public String getEventDate() { return eventDate; }
    public String getEventLocation() { return eventLocation; }
    public String getEventPrice() { return eventPrice; } // YENİ
    public int getImageResourceId() { return imageResourceId; }
}