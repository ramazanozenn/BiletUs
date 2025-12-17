package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.biletus.app.databinding.FragmentFavoritesBinding;
import java.util.ArrayList;

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

        // 1. Favori Listesini Manager'dan Al
        ArrayList<EventModel> myFavorites = FavoritesManager.getInstance().getFavorites();

        // 2. Adapter Kur (Aynı HomeFragment'taki gibi)
        adapter = new EventAdapter(myFavorites);

        // Favorilerden detay sayfasına gitmek istersen yine tıklama ekleyebilirsin:
        adapter.setOnItemClickListener(event -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected_event", event);
            androidx.navigation.Navigation.findNavController(view)
                    .navigate(R.id.eventDetailFragment, bundle);
        });

        // 3. Listeyi Göster
        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favoritesRecyclerView.setAdapter(adapter);
    }

    // Sayfaya her geri dönüldüğünde listeyi yenilemek için (örneğin detayda favoriden çıkarırsan)
    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}