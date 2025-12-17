package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.biletus.app.databinding.FragmentEventDetailBinding;

public class EventDetailFragment extends Fragment {

    private FragmentEventDetailBinding binding;
    private boolean isFavorite = false;
    private EventModel currentEvent; // Dinamik veri nesnemiz

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. GELEN VERİYİ AL (PAKETİ AÇ)
        if (getArguments() != null) {
            // "selected_event" anahtarıyla gönderilen paketi açıyoruz
            currentEvent = (EventModel) getArguments().getSerializable("selected_event");
        }

        // 2. EKRANA BAS (DİNAMİK KISIM)
        if (currentEvent != null) {
            // Başlık
            binding.txtDetailTitle.setText(currentEvent.getEventName());

            // Tarih ve Konum
            binding.txtDetailInfo.setText("📅 " + currentEvent.getEventDate() + "  •  📍 " + currentEvent.getEventLocation());

            // Açıklama (Dinamik İsimle)
            String description = "Prepare for an unforgettable night with " + currentEvent.getEventName() + "! Experience the energy of music with a spectacular stage performance...";
            binding.txtDetailDescription.setText(description);

            // Fiyat
            binding.txtDetailPrice.setText(currentEvent.getEventPrice());

            // Resim
            binding.imgDetailHeader.setImageResource(currentEvent.getImageResourceId());

            // Sayfa açılınca favori durumunu kontrol et (Dolu mu boş mu olacak?)
            checkIfFavorite();
        }

        // 3. BUTONLARI AYARLA (Kodlar metodun içinde olmalı!)

        // --- FAVORİ BUTONU ---
        binding.btnFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                // Favoriden çıkar -> BOŞ KALP
                FavoritesManager.getInstance().removeFavorite(currentEvent);
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(requireContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
                isFavorite = false;
            } else {
                // Favoriye ekle -> DOLU (KIRMIZI) KALP
                FavoritesManager.getInstance().addFavorite(currentEvent);
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_red);
                Toast.makeText(requireContext(), "Added to Favorites ❤️", Toast.LENGTH_SHORT).show();
                isFavorite = true;
            }
        });

        // --- GERİ BUTONU ---
        binding.btnBack.setOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        // --- SATIN AL BUTONU ---
        binding.btnBuyTicket.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_eventDetailFragment_to_ticketSelectionFragment);
            Toast.makeText(requireContext(), "Ticket Added to Cart! 🎟️", Toast.LENGTH_SHORT).show();
        });
    }

    // Yardımcı Metot: Başlangıçta kalbin durumunu belirler
    private void checkIfFavorite() {
        if (currentEvent != null && FavoritesManager.getInstance().isFavorite(currentEvent)) {
            isFavorite = true;
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_red);
        } else {
            isFavorite = false;
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}