package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.biletus.app.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArrayList<EventModel> eventList; // Değişkenleri burada tanımlıyoruz
    private EventAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Verileri Oluştur
        createDummyData();

        // 2. Adapter'ı Hazırla
        adapter = new EventAdapter(eventList);

        // 3. TIKLAMA OLAYINI YAKALA (Dinleyici)
        adapter.setOnItemClickListener(event -> {
            // Tıklanan etkinliği paketle (Bundle)
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected_event", event);

            // Detay sayfasına paketle beraber git
            Navigation.findNavController(view)
                    .navigate(R.id.eventDetailFragment, bundle);
        });

        // 4. RecyclerView Ayarları ve Bağlama
        // XML dosyanızdaki ID 'eventsRecyclerView' olmalı.
        binding.recyclerViewEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewEvents.setHasFixedSize(true);
        binding.recyclerViewEvents.setAdapter(adapter);
    }

    private void createDummyData() {
        eventList = new ArrayList<>();

        // Artık modelimiz FİYAT bilgisini de (4. sırada) kabul ediyor:
        eventList.add(new EventModel(
                "Hayko Cepkin Concert",
                "14 Oct • 21:00",
                "Maximum Uniq Hall",
                "650 TL",
                R.drawable.img_hayko)); // Veya R.drawable.ic_launcher_background

        eventList.add(new EventModel(
                "MilyonFest Istanbul",
                "20 Nov • 14:00",
                "Milyon Beach Kilyos",
                "800 TL",
                R.drawable.img_milyon));

        eventList.add(new EventModel(
                "Teoman",
                "05 Dec • 21:00",
                "Harbiye Açıkhava",
                "500 TL",
                R.drawable.img_teoman)); // Uygun resim yoksa diğerlerini kullan
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}