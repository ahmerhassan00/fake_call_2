package com.example.house.fakecall_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(() -> {

            SharedPreferences sharedPreferences = getSharedPreferences("check_entry", MODE_PRIVATE);
            boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);

            if (isFirstTime) {
                startActivity(new Intent(this, new_user_privacy.class));


            } else {
                startActivity(new Intent(this, welcomescreen.class));
            }
            finish();

        }, 2000);


    }
}