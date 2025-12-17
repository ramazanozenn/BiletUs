package com.biletus.app;

public class FavoritesModel {
    private String name;
    private String date;
    private String location;
    private String time;
    private String price;
    private int imageResourceId;

    public FavoritesModel(String name, String date, String location, String time, String price, int imageResourceId) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.time = time;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getTime() { return time; }
    public String getPrice() { return price; }
    public int getImageResourceId() { return imageResourceId; }
}