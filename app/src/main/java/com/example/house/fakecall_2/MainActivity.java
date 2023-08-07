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

            // Inside your main launcher activity (e.g., MainActivity)
            SharedPreferences sharedPreferences = getSharedPreferences("check_entry", MODE_PRIVATE);
            boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);

            if (isFirstTime) {
                // First-time user, navigate to Activity 1
                startActivity(new Intent(this, new_user_privacy.class));

                // Set the flag to false to indicate the app has been opened once
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isFirstTime", false);
                editor.apply();
            } else {
                // Not the first time, navigate to Activity 2
                startActivity(new Intent(this, welcomescreen.class));
            }

// Finish the current activity to prevent the user from navigating back to it
            finish();

        }, 2000);


    }
}