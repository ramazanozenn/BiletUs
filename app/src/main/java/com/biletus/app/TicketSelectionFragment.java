


package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
//Gizem



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.biletus.app.databinding.FragmentTicketSelectionBinding;

public class TicketSelectionFragment extends Fragment {

    private FragmentTicketSelectionBinding binding;

    private int countStudent = 0;
    private int countAdult = 0;


    private int priceStudent = 0;
    private int priceAdult = 0;

    private EventModel currentEvent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketSelectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            currentEvent = (EventModel) getArguments().getSerializable("selected_event");

            if (currentEvent != null) {
                binding.txtEventNameSmall.setText(currentEvent.getEventName());
                binding.txtEventDateSmall.setText("📅 " + currentEvent.getEventDate());

                if (currentEvent.getImageResourceId() != 0) {
                    binding.imgEventSmall.setImageResource(currentEvent.getImageResourceId());
                }

                String rawPrice = currentEvent.getEventPrice();
                priceAdult = parsePrice(rawPrice);
                priceStudent = (priceAdult > 100) ? (priceAdult - 100) : (priceAdult / 2);
            }
        }

        binding.txtPriceStudentLabel.setText(priceStudent + " TL");
        binding.txtPriceAdultLabel.setText(priceAdult + " TL");

        binding.btnStudentPlus.setOnClickListener(v -> { countStudent++; updateUI(); });
        binding.btnStudentMinus.setOnClickListener(v -> { if(countStudent>0) countStudent--; updateUI(); });

        binding.btnAdultPlus.setOnClickListener(v -> { countAdult++; updateUI(); });
        binding.btnAdultMinus.setOnClickListener(v -> { if(countAdult>0) countAdult--; updateUI(); });

        binding.btnConfirmPayment.setOnClickListener(v -> {
            int total = (countStudent * priceStudent) + (countAdult * priceAdult);
            if (total == 0) {
                Toast.makeText(requireContext(), "Please add at least one ticket!", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("total_price", total);
            bundle.putSerializable("selected_event", currentEvent);

            Navigation.findNavController(v).navigate(R.id.action_ticketSelectionFragment_to_paymentFragment, bundle);
        });

        binding.btnBack.setOnClickListener(v -> requireActivity().getOnBackPressedDispatcher().onBackPressed());

        updateUI();
    }

    private int parsePrice(String priceString) {
        try {
            if (priceString == null) return 500;
            String numberOnly = priceString.replaceAll("[^0-9]", "");
            return numberOnly.isEmpty() ? 500 : Integer.parseInt(numberOnly);
        } catch (Exception e) { return 500; }
    }

    private void updateUI() {
        binding.txtStudentCount.setText(String.valueOf(countStudent));
        binding.txtAdultCount.setText(String.valueOf(countAdult));
        int total = (countStudent * priceStudent) + (countAdult * priceAdult);
        binding.txtTotalPrice.setText(total + " TL");
        binding.btnConfirmPayment.setText(total > 0 ? "Pay " + total + " TL" : "Confirm Payment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}