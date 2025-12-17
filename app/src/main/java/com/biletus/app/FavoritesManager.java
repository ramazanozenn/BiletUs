package com.biletus.app;

import java.util.ArrayList;

public class FavoritesManager {

    // Tek bir yönetici olsun (Singleton yapısı)
    private static FavoritesManager instance;
    private ArrayList<EventModel> favoriteList; // Listemiz EventModel tutacak

    private FavoritesManager() {
        favoriteList = new ArrayList<>();
    }

    public static synchronized FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    // Favorileri getir
    public ArrayList<EventModel> getFavorites() {
        return favoriteList;
    }

    // Favoriye ekle
    public void addFavorite(EventModel event) {
        // Eğer boş değilse ve zaten listede yoksa ekle
        if (event != null && !isFavorite(event)) {
            favoriteList.add(event);
        }
    }

    // Favoriden çıkar
    public void removeFavorite(EventModel event) {
        if (event == null) return;

        for (int i = 0; i < favoriteList.size(); i++) {
            // İsimleri eşleşiyorsa sil
            if (favoriteList.get(i).getEventName().equals(event.getEventName())) {
                favoriteList.remove(i);
                break;
            }
        }
    }

    // Kontrol et: Bu etkinlik favorilerde var mı?
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