package com.biletus.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        // 3 Saniye (3000 milisaniye) bekle ve MainActivity'ye geç
        new Handler().postDelayed(() -> {
            // Ana sayfaya geçiş niyetini (Intent) oluştur
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            // Geri tuşuna basınca tekrar Splash ekranına dönmemesi için bu aktiviteyi öldür
            finish();
        }, 3000);
    }
}