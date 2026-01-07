//Coded by Ramazan



package com.biletus.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<FavoritesModel> favoriteList;

    public FavoritesAdapter(List<FavoritesModel> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoritesModel item = favoriteList.get(position);

        holder.txtName.setText(item.getName());
        holder.txtDate.setText(item.getDate());
        holder.txtLocation.setText(item.getLocation());
        holder.txtTime.setText(item.getTime());
        holder.txtPrice.setText(item.getPrice());
        holder.imgEvent.setImageResource(item.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDate, txtLocation, txtTime, txtPrice;
        ImageView imgEvent;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_fav_name);
            txtDate = itemView.findViewById(R.id.txt_fav_date);
            txtLocation = itemView.findViewById(R.id.txt_fav_location);
            txtTime = itemView.findViewById(R.id.txt_fav_time);
            txtPrice = itemView.findViewById(R.id.txt_fav_price);
            imgEvent = itemView.findViewById(R.id.img_fav_event);
        }
    }
}