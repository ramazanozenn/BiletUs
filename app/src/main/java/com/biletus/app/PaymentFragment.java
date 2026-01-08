//Gizem



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

import com.biletus.app.databinding.FragmentPaymentBinding;

public class PaymentFragment extends Fragment {

    private FragmentPaymentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int totalPrice = 0;
        EventModel currentEvent = null;

        if (getArguments() != null) {
            totalPrice = getArguments().getInt("total_price", 0);
            currentEvent = (EventModel) getArguments().getSerializable("selected_event");
        }

        binding.btnCompletePayment.setText("PAY " + totalPrice + " TL");

        if (currentEvent != null) {
            if (binding.txtEventName != null)
                binding.txtEventName.setText(currentEvent.getEventName());

            if (binding.txtEventDate != null)
                binding.txtEventDate.setText(currentEvent.getEventDate());

            if (binding.imgEventHeader != null && currentEvent.getImageResourceId() != 0) {
                binding.imgEventHeader.setImageResource(currentEvent.getImageResourceId());
            }
        }

        binding.btnCompletePayment.setOnClickListener(v -> {

            String cardNumber = binding.etCardNumber.getText().toString();
            String cardHolder = binding.etCardHolder.getText().toString();
            String expiry = binding.etExpiryDate.getText().toString();
            String cvv = binding.etCvv.getText().toString();

            if (cardNumber.isEmpty() || cardHolder.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all card details!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(requireContext(), "Payment Successful! Enjoy the concert! 🎉", Toast.LENGTH_LONG).show();
            Navigation.findNavController(v).popBackStack(R.id.homeFragment, false);
        });

        if (binding.btnBack != null) {
            binding.btnBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}