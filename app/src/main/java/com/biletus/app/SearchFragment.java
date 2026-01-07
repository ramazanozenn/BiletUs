
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

import com.biletus.app.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private EventAdapter adapter;
    private List<EventModel> allEvents;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadData();


        adapter = new EventAdapter(new ArrayList<>());
        binding.searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.searchRecyclerView.setAdapter(adapter);

        if (binding.txtRecentTitle != null) {
            binding.txtRecentTitle.setVisibility(View.GONE);
        }

        adapter.setOnItemClickListener(event -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected_event", event);
            Navigation.findNavController(view).navigate(R.id.eventDetailFragment, bundle);
        });

        binding.searchInput.addTextChangedListener(new TextWatcher() {
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
        if (text.isEmpty()) {
            adapter.setFilteredList(new ArrayList<>());
            if (binding.txtRecentTitle != null) {
                binding.txtRecentTitle.setVisibility(View.GONE);
            }
        } else {
            List<EventModel> filteredList = new ArrayList<>();
            for (EventModel item : allEvents) {
                if (item.getEventName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }

            adapter.setFilteredList(filteredList);

            if (binding.txtRecentTitle != null) {
                binding.txtRecentTitle.setVisibility(View.VISIBLE);
                if (filteredList.isEmpty()) {
                    binding.txtRecentTitle.setText("No results found");
                } else {
                    binding.txtRecentTitle.setText("Search Results");
                }
            }
        }
    }

    private void loadData() {
        allEvents = new ArrayList<>();


        allEvents.add(new EventModel(
                "Hayko Cepkin Concert",
                "14 Oct • 21:00",
                "Maximum Uniq Hall",
                "750 TL",
                R.drawable.img_hayko
        ));

        allEvents.add(new EventModel(
                "MilyonFest Istanbul",
                "20 Nov • 14:00",
                "Milyon Beach Kilyos",
                "400 TL",
                R.drawable.img_milyon
        ));

        allEvents.add(new EventModel(
                "Teoman",
                "05 Dec • 21:00",
                "Harbiye Açıkhava",
                "500 TL",
                R.drawable.img_teoman
        ));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}