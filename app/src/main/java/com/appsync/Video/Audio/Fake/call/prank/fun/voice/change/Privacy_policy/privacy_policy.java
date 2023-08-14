package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Privacy_policy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;

public class privacy_policy extends AppCompatActivity {

    RelativeLayout continuebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        continuebtn = findViewById(R.id.privacy_continuebtn);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}