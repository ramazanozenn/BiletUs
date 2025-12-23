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

        binding.btnCompletePayment.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Payment Successful! Enjoy the concert! 🎉", Toast.LENGTH_LONG).show();

            Navigation.findNavController(v).popBackStack(R.id.homeFragment, false);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}