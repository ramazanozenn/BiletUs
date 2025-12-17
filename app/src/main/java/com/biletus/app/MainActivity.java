package com.biletus.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.biletus.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Binding ile tasarımı yüklüyoruz (TEK SEFER)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Navigasyon Yöneticisini (Controller) Buluyoruz
        // FragmentContainerView kullandığımız için yöntemimiz bu olmalı:
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            // 3. Alt Menü ile Navigasyonu Birleştiriyoruz
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
        }
    }
}