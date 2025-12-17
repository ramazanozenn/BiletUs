package com.biletus.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.HistoryViewHolder> {

    private List<SearchHistoryModel> historyList;

    public SearchHistoryAdapter(List<SearchHistoryModel> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Yeni tasarım dosyamızı (list_item_search_history) bağlıyoruz
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_search_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        SearchHistoryModel item = historyList.get(position);
        holder.txtHistory.setText(item.getSearchText());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtHistory;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // Tasarımdaki ID'yi (txt_search_history) buluyoruz
            txtHistory = itemView.findViewById(R.id.txt_search_history);
        }
    }
}