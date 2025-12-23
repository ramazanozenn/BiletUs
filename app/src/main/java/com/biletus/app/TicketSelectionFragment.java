package com.biletus.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.biletus.app.databinding.FragmentTicketSelectionBinding;

public class TicketSelectionFragment extends Fragment {

    private FragmentTicketSelectionBinding binding;

    private final int PRICE_STUDENT = 450;
    private final int PRICE_ADULT = 650;

    private int currentQty = 1;
    private boolean isAdultSelected = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketSelectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateUI();


        binding.rowStudent.setOnClickListener(v -> {
            isAdultSelected = false; // Artık Student seçili
            currentQty = 1;          // Adedi sıfırla
            updateUI();              // Ekranı yenile
        });

        binding.rowAdult.setOnClickListener(v -> {
            isAdultSelected = true;  // Adult seçili
            currentQty = 1;          // Adedi sıfırla
            updateUI();              // Ekranı yenile
        });


        binding.btnMinusStudent.setOnClickListener(v -> {
            if (currentQty > 1) {
                currentQty--;
                updateUI();
            }
        });
        binding.btnPlusStudent.setOnClickListener(v -> {
            currentQty++;
            updateUI();
        });

        binding.btnMinusAdult.setOnClickListener(v -> {
            if (currentQty > 1) {
                currentQty--;
                updateUI();
            }
        });
        binding.btnPlusAdult.setOnClickListener(v -> {
            currentQty++;
            updateUI();
        });

        binding.btnBackSelection.setOnClickListener(v ->
                requireActivity().getOnBackPressedDispatcher().onBackPressed()
        );

        binding.btnGoPayment.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_ticketSelectionFragment_to_paymentFragment);
        });
    }

    private void updateUI() {
        int totalPrice;

        if (isAdultSelected) {
            binding.layoutCounterAdult.setVisibility(View.VISIBLE);
            binding.layoutCounterStudent.setVisibility(View.GONE);

            totalPrice = currentQty * PRICE_ADULT;

            binding.txtQtyAdult.setText(String.valueOf(currentQty));
            binding.txtPriceAdult.setText(totalPrice + " TL");

            binding.txtPriceStudent.setText(PRICE_STUDENT + " TL");

        } else {
            binding.layoutCounterStudent.setVisibility(View.VISIBLE);
            binding.layoutCounterAdult.setVisibility(View.GONE);

            totalPrice = currentQty * PRICE_STUDENT;

            binding.txtQtyStudent.setText(String.valueOf(currentQty));
            binding.txtPriceStudent.setText(totalPrice + " TL");

            binding.txtPriceAdult.setText(PRICE_ADULT + " TL");
        }

        binding.btnGoPayment.setText("TOTAL PRICE: " + totalPrice + " TL\nGO TO PAYMENT");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}