package com.biletus.app;

import java.util.ArrayList;

public class FavoritesManager {

    private static FavoritesManager instance;
    private ArrayList<EventModel> favoriteList;

    private FavoritesManager() {
        favoriteList = new ArrayList<>();
    }

    public static synchronized FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    public ArrayList<EventModel> getFavorites() {
        return favoriteList;
    }

    public void addFavorite(EventModel event) {
        if (event != null && !isFavorite(event)) {
            favoriteList.add(event);
        }
    }

    public void removeFavorite(EventModel event) {
        if (event == null) return;

        for (int i = 0; i < favoriteList.size(); i++) {
            if (favoriteList.get(i).getEventName().equals(event.getEventName())) {
                favoriteList.remove(i);
                break;
            }
        }
    }

    public boolean isFavorite(EventModel event) {
        if (event == null) return false;

        for (EventModel e : favoriteList) {
            if (e.getEventName().equals(event.getEventName())) {
                return true;
            }
        }
        return false;
    }
}