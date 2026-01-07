//Coded by Ramazan



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

        List<EventModel> myFavorites = FavoritesManager.getInstance().getFavorites();

        adapter = new EventAdapter(myFavorites);

        adapter.setOnItemClickListener(event -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected_event", event);

            Navigation.findNavController(view)
                    .navigate(R.id.eventDetailFragment, bundle);
        });

        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favoritesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
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