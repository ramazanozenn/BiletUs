package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.biletus.app.databinding.FragmentEventDetailBinding;

public class EventDetailFragment extends Fragment {

    private FragmentEventDetailBinding binding;
    private boolean isFavorite = false;
    private EventModel currentEvent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null) {
            currentEvent = (EventModel) getArguments().getSerializable("selected_event");
        }


        if (currentEvent != null) {

            binding.txtDetailTitle.setText(currentEvent.getEventName());

            binding.txtDetailInfo.setText("📅 " + currentEvent.getEventDate() + "  •  📍 " + currentEvent.getEventLocation());

            String description = "Prepare for an unforgettable night with " + currentEvent.getEventName() + "! Experience the energy of music with a spectacular stage performance...";
            binding.txtDetailDescription.setText(description);

            binding.txtDetailPrice.setText(currentEvent.getEventPrice());

            binding.imgDetailHeader.setImageResource(currentEvent.getImageResourceId());

            checkIfFavorite();
        }


        binding.btnFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                FavoritesManager.getInstance().removeFavorite(currentEvent);
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(requireContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
                isFavorite = false;
            } else {
                FavoritesManager.getInstance().addFavorite(currentEvent);
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_red);
                Toast.makeText(requireContext(), "Added to Favorites ❤️", Toast.LENGTH_SHORT).show();
                isFavorite = true;
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        // EventDetailFragment.java içinde ilgili kısmı bul ve bununla değiştir:

        binding.btnBuyTicket.setOnClickListener(v -> {
            // 1. Bir çanta (Bundle) oluştur
            Bundle bundle = new Bundle();

            // 2. İçine seçilen etkinliği koy (Anahtar kelime: "selected_event")
            bundle.putSerializable("selected_event", currentEvent);

            // 3. Sayfayı değiştirirken çantayı da yanına ver
            Navigation.findNavController(v).navigate(R.id.action_eventDetailFragment_to_ticketSelectionFragment, bundle);

            // Mesaj ver
            Toast.makeText(requireContext(), "Ticket Selection Started 🎟️", Toast.LENGTH_SHORT).show();
        });
    }

    private void checkIfFavorite() {
        if (currentEvent != null && FavoritesManager.getInstance().isFavorite(currentEvent)) {
            isFavorite = true;
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_red);
        } else {
            isFavorite = false;
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}