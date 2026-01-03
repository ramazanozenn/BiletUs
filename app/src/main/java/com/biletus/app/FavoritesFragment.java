package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation; // Navigasyon için gerekli
import androidx.recyclerview.widget.LinearLayoutManager;

import com.biletus.app.databinding.FragmentFavoritesBinding;

import java.util.List; // Sadece List kullanıyoruz, ArrayList değil

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private EventAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Veriyi 'List' türünde çekiyoruz (Firebase Manager List döndürüyor)
        List<EventModel> myFavorites = FavoritesManager.getInstance().getFavorites();

        // 2. Adapter'a bu listeyi veriyoruz
        // (EventAdapter dosyasında ArrayList yerine List yaptığından emin ol)
        adapter = new EventAdapter(myFavorites);

        // 3. Tıklama Olayı (Detay sayfasına git)
        adapter.setOnItemClickListener(event -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected_event", event);

            // Eğer 'eventDetailFragment' ID'si nav_graph.xml'de varsa çalışır.
            // Yoksa oradaki action ID'sini kullanman gerekebilir (örn: R.id.action_favorites_to_detail)
            Navigation.findNavController(view)
                    .navigate(R.id.eventDetailFragment, bundle);
        });

        // 4. RecyclerView Ayarları
        // XML dosyasındaki ID'nin 'favoritesRecyclerView' olduğundan emin ol
        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favoritesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Sayfa tekrar ekrana gelince listeyi yenile (Firebase verisi güncellenmiş olabilir)
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}