package com.example.house.fakecall_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class terms_and_conditions extends AppCompatActivity {

    CheckBox ch1,ch2;
    Button btn_continue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        ch1 = findViewById(R.id.chkbx1);
        ch2= findViewById(R.id.chkbx2);
        btn_continue = findViewById(R.id.terms_continue);
        SharedPreferences sharedPreferences = getSharedPreferences("check_entry", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getContinue();
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContinue();
            }
        });

        if (ch1.isChecked() && ch2.isChecked()){
            btn_continue.setVisibility(View.VISIBLE);
        }

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the flag to false to indicate the app has been opened once
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isFirstTime", false);
                editor.apply();
                Intent i = new Intent(terms_and_conditions.this, welcomescreen.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void getContinue() {
        if (ch1.isChecked() && ch2.isChecked()){
            btn_continue.setVisibility(View.VISIBLE);
        }
        else{
            btn_continue.setVisibility(View.GONE);
        }
    }
}