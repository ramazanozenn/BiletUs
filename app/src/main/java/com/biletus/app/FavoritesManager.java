//Coded by Ramazan



package com.biletus.app;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static FavoritesManager instance;
    private DatabaseReference databaseRef;
    private List<EventModel> localFavoritesList;

    private FavoritesManager() {
        databaseRef = FirebaseDatabase.getInstance().getReference("favorites").child("user_1");
        localFavoritesList = new ArrayList<>();

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                localFavoritesList.clear();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    EventModel event = keyNode.getValue(EventModel.class);
                    if (event != null) {
                        localFavoritesList.add(event);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Firebase Hatası: " + error.getMessage());
            }
        });
    }

    public static synchronized FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    public List<EventModel> getFavorites() {
        return localFavoritesList;
    }

    public void addFavorite(EventModel event) {
        if (event != null) {
            databaseRef.child(event.getEventName()).setValue(event);
        }
    }

    public void removeFavorite(EventModel event) {
        if (event != null) {
            databaseRef.child(event.getEventName()).removeValue();
        }
    }

    public boolean isFavorite(EventModel event) {
        if (event == null) return false;

        for (EventModel e : localFavoritesList) {
            if (e.getEventName().equals(event.getEventName())) {
                return true;
            }
        }
        return false;
    }
}