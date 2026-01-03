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
    private List<EventModel> localFavoritesList; // Verileri geçici olarak burada da tutacağız ki uygulama hızlı çalışsın

    private FavoritesManager() {
        // 1. Firebase Veritabanına Bağlan
        // "favorites" klasörü -> "user_1" alt klasörü
        databaseRef = FirebaseDatabase.getInstance().getReference("favorites").child("user_1");
        localFavoritesList = new ArrayList<>();

        // 2. Veritabanını Sürekli Dinle (Realtime Listener)
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Veri her değiştiğinde (ekleme/silme) burası çalışır
                localFavoritesList.clear(); // Listeyi temizle
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    // Gelen veriyi EventModel'e çevir
                    EventModel event = keyNode.getValue(EventModel.class);
                    if (event != null) {
                        localFavoritesList.add(event);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hata olursa buraya düşer (Şimdilik boş bırakabiliriz)
                System.out.println("Firebase Hatası: " + error.getMessage());
            }
        });
    }

    // Singleton Yapısı
    public static synchronized FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    // Listeyi Getir
    public List<EventModel> getFavorites() {
        return localFavoritesList;
    }

    // Ekleme
    public void addFavorite(EventModel event) {
        if (event != null) {
            // Etkinlik ismini "Anahtar" (Key) olarak kullanıyoruz
            // Böylece aynı isimde iki kayıt olmaz
            databaseRef.child(event.getEventName()).setValue(event);
        }
    }

    // Silme
    public void removeFavorite(EventModel event) {
        if (event != null) {
            databaseRef.child(event.getEventName()).removeValue();
        }
    }

    // Kontrol Etme (Bu favorilerde var mı?)
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