package com.example.house.fakecall_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class new_user_privacy extends AppCompatActivity {

    Button MoveNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_privacy);

        MoveNext = findViewById(R.id.new_privacy_continuebtn);
         MoveNext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(new_user_privacy.this,welcomescreen.class);
                 startActivity(i);
                 finish();
             }
         });
    }
}