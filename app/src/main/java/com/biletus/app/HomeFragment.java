//Coded by Ramazan



package com.biletus.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EventAdapter adapter;

    private List<EventModel> eventList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (eventList == null) {
            eventList = new ArrayList<>();
            loadData();
        }

        adapter = new EventAdapter(eventList);

        binding.recyclerViewEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewEvents.setAdapter(adapter);

        adapter.setOnItemClickListener(event -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected_event", event);
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_eventDetailFragment, bundle);
        });

        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterList(String text) {
        List<EventModel> filteredList = new ArrayList<>();
        if (eventList == null) return;

        for (EventModel item : eventList) {
            if (item.getEventName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.setFilteredList(filteredList);
    }

    private void loadData() {
        new Thread(() -> {
            List<EventModel> tempList = new ArrayList<>();

            tempList.add(new EventModel("Hayko Cepkin Concert", "14 Oct • 21:00", "Maximum Uniq Hall", "750 TL", R.drawable.img_hayko));
            tempList.add(new EventModel("MilyonFest Istanbul", "20 Nov • 14:00", "Milyon Beach Kilyos", "400 TL", R.drawable.img_milyon));
            tempList.add(new EventModel("Teoman", "05 Dec • 21:00", "Harbiye Açıkhava", "500 TL", R.drawable.img_teoman));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    if (eventList != null) {
                        eventList.clear();
                        eventList.addAll(tempList);
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}