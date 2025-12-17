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

    // Fiyat Sabitleri
    private final int PRICE_STUDENT = 450;
    private final int PRICE_ADULT = 650;

    // Değişkenler
    private int currentQty = 1;      // Başlangıç adedi
    private boolean isAdultSelected = true; // Başlangıçta Adult seçili

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketSelectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // İlk açılışta ekranı güncelle
        updateUI();

        // --- SATIR SEÇİMLERİ ---

        // Student Satırına Tıklayınca
        binding.rowStudent.setOnClickListener(v -> {
            isAdultSelected = false; // Artık Student seçili
            currentQty = 1;          // Adedi sıfırla
            updateUI();              // Ekranı yenile
        });

        // Adult Satırına Tıklayınca
        binding.rowAdult.setOnClickListener(v -> {
            isAdultSelected = true;  // Adult seçili
            currentQty = 1;          // Adedi sıfırla
            updateUI();              // Ekranı yenile
        });

        // --- ARTI / EKSİ BUTONLARI ---

        // Student Eksi
        binding.btnMinusStudent.setOnClickListener(v -> {
            if (currentQty > 1) {
                currentQty--;
                updateUI();
            }
        });
        // Student Artı
        binding.btnPlusStudent.setOnClickListener(v -> {
            currentQty++;
            updateUI();
        });

        // Adult Eksi
        binding.btnMinusAdult.setOnClickListener(v -> {
            if (currentQty > 1) {
                currentQty--;
                updateUI();
            }
        });
        // Adult Artı
        binding.btnPlusAdult.setOnClickListener(v -> {
            currentQty++;
            updateUI();
        });

        // --- NAVİGASYON ---
        binding.btnBackSelection.setOnClickListener(v ->
                requireActivity().getOnBackPressedDispatcher().onBackPressed()
        );

        binding.btnGoPayment.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_ticketSelectionFragment_to_paymentFragment);
        });
    }

    // Ekranı Güncelleyen Fonksiyon
    private void updateUI() {
        int totalPrice;

        if (isAdultSelected) {
            // ADULT MODU
            binding.layoutCounterAdult.setVisibility(View.VISIBLE);
            binding.layoutCounterStudent.setVisibility(View.GONE);

            // Fiyat Hesapla: 2 x 650 = 1300
            totalPrice = currentQty * PRICE_ADULT;

            // Yazıları Güncelle
            binding.txtQtyAdult.setText(String.valueOf(currentQty));
            binding.txtPriceAdult.setText(totalPrice + " TL");

            // Diğerini varsayılana döndür
            binding.txtPriceStudent.setText(PRICE_STUDENT + " TL");

        } else {
            // STUDENT MODU
            binding.layoutCounterStudent.setVisibility(View.VISIBLE);
            binding.layoutCounterAdult.setVisibility(View.GONE);

            // Fiyat Hesapla: 2 x 450 = 900
            totalPrice = currentQty * PRICE_STUDENT;

            // Yazıları Güncelle
            binding.txtQtyStudent.setText(String.valueOf(currentQty));
            binding.txtPriceStudent.setText(totalPrice + " TL");

            // Diğerini varsayılana döndür
            binding.txtPriceAdult.setText(PRICE_ADULT + " TL");
        }

        // ALT BUTONU GÜNCELLE
        binding.btnGoPayment.setText("TOTAL PRICE: " + totalPrice + " TL\nGO TO PAYMENT");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}