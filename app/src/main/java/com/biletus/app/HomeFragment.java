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
    private ArrayList<EventModel> eventList;
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

        createDummyData();

        adapter = new EventAdapter(eventList);

        adapter.setOnItemClickListener(event -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected_event", event);

            Navigation.findNavController(view)
                    .navigate(R.id.eventDetailFragment, bundle);
        });

        binding.recyclerViewEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewEvents.setHasFixedSize(true);
        binding.recyclerViewEvents.setAdapter(adapter);
    }

    private void createDummyData() {
        eventList = new ArrayList<>();

        eventList.add(new EventModel(
                "Hayko Cepkin Concert",
                "14 Oct • 21:00",
                "Maximum Uniq Hall",
                "650 TL",
                R.drawable.img_hayko));

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
                R.drawable.img_teoman));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}