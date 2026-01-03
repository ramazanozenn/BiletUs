package com.biletus.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<EventModel> eventList;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(EventModel event);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public EventAdapter(List<EventModel> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = eventList.get(position);
        holder.txtName.setText(event.getEventName());
        holder.txtDate.setText(event.getEventDate() + " - " + event.getEventLocation());
        holder.imgEvent.setImageResource(event.getImageResourceId());

        // KARTIN TAMAMINA TIKLAMA OLAYI
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDate;
        ImageView imgEvent;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_event_name);
            txtDate = itemView.findViewById(R.id.txt_event_location);
            imgEvent = itemView.findViewById(R.id.img_event);
        }
    }
}