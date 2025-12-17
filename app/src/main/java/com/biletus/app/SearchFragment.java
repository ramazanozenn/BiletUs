package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager; // Normal liste görünümü

import com.biletus.app.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupHistoryList();
    }

    private void setupHistoryList() {
        // 1. VERİLER (Resimdeki örnekler)
        List<SearchHistoryModel> historyList = new ArrayList<>();
        historyList.add(new SearchHistoryModel("Hayko Cepkin Concert"));
        historyList.add(new SearchHistoryModel("MilyonFest Festival"));
        historyList.add(new SearchHistoryModel("Limon Theatre Show"));
        historyList.add(new SearchHistoryModel("Tarkan Harbiye"));
        historyList.add(new SearchHistoryModel("Istanbul Modern Art"));

        // 2. ADAPTÖR
        SearchHistoryAdapter adapter = new SearchHistoryAdapter(historyList);

        // 3. RECYCLERVIEW AYARLARI
        // XML dosyasında verdiğimiz ID: recent_searches_recycler
        binding.recentSearchesRecycler.setHasFixedSize(true);

        // LinearLayoutManager: Alt alta liste yapar
        binding.recentSearchesRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.recentSearchesRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}