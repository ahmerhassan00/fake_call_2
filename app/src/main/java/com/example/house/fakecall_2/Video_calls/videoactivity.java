package com.example.house.fakecall_2.Video_calls;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.house.fakecall_2.Audio_calls.Whatsapp_audiocall;
import com.example.house.fakecall_2.Audio_calls.audioactivity;
import com.example.house.fakecall_2.Audio_calls.messanger_audio_call;
import com.example.house.fakecall_2.R;
import com.example.house.fakecall_2.home;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;

public class videoactivity extends AppCompatActivity {

    AdView mAdView;
    int count = 0;
    TextView time,medium;
    int number ,number1;

    ImageView back,timeNext,timeBack,SrcNext,SrcBack,startProcess;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoactivity);
        back = findViewById(R.id.backbtn);

        timeNext = findViewById(R.id.video_time_next);
        timeBack = findViewById(R.id.video_time_back);
        SrcNext = findViewById(R.id.video_medium_next);
        SrcBack = findViewById(R.id.video_medium_back);
        time = findViewById(R.id.video_txt2);
        medium = findViewById(R.id.video_txt3);
        startProcess = findViewById(R.id.start_video_call);
        String[] toggleOptions = {"Whatsapp", "Messanger"};
        final int[] currentIndex = {0};

        String[] toggleTime = {"Now", "10s", "20s", "30s", "1m", "5m"};
        final int[] currentIndextime = {0};

        //camera permission
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission if not granted
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 1);
        }
        timeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setText(toggleTime[currentIndextime[0]]);

                currentIndextime[0] = (currentIndextime[0] + 1) % toggleTime.length;
            }
        });
        timeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setText(toggleTime[currentIndextime[0]]);

                currentIndextime[0] = (currentIndextime[0] + 1) % toggleTime.length;
            }
        });

        SrcNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medium.setText(toggleOptions[currentIndex[0]]);

                currentIndex[0] = (currentIndex[0] + 1) % toggleOptions.length;

            }
        });
        SrcBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medium.setText(toggleOptions[currentIndex[0]]);

                currentIndex[0] = (currentIndex[0] + 1) % toggleOptions.length;

            }
        });

        startProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (medium.getText().equals("Messanger")){

                    if (time.getText().toString().equals("Now")){
                        number = 0;
                    }
                    else if (time.getText().toString().equals("10s")) {
                        number = 10000;
                        Toast.makeText(videoactivity.this, "Call will start after 10 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    }
                    else if (time.getText().toString().equals("20s")) {
                        number = 20000;
                        Toast.makeText(videoactivity.this, "Call will start after 20 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    }
                    else if (time.getText().toString().equals("30s")) {
                        number = 30000;
                        Toast.makeText(videoactivity.this, "Call will start after 30 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    }
                    else if (time.getText().toString().equals("1m")) {
                        number = 60000;
                        Toast.makeText(videoactivity.this, "Call will start after 1 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    }
                    else if (time.getText().toString().equals("5m")) {
                        number = 300000;
                        Toast.makeText(videoactivity.this, "Call will start after 5 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        Intent i = new Intent(videoactivity.this, messanger_video_call.class);
                        startActivity(i);
                        finish();

                    },number);

                } else if (medium.getText().equals("Whatsapp")) {

                    if (time.getText().toString().equals("Now")) {

                        number1 = 0;
                    } else if (time.getText().toString().equals("10s")) {
                        number1 = 10000;
                        Toast.makeText(videoactivity.this, "Call will start after 10 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    } else if (time.getText().toString().equals("20s")) {
                        number1 = 20000;
                        Toast.makeText(videoactivity.this, "Call will start after 20 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    } else if (time.getText().toString().equals("30s")) {
                        number1 = 30000;
                        Toast.makeText(videoactivity.this, "Call will start after 30 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    } else if (time.getText().toString().equals("1m")) {
                        number1 = 60000;
                        Toast.makeText(videoactivity.this, "Call will start after 1 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    } else if (time.getText().toString().equals("5m")) {
                        number1 = 300000;
                        Toast.makeText(videoactivity.this, "Call will start after 5 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        Intent i = new Intent(videoactivity.this, whatsapp_video_call.class);
                        startActivity(i);
                        finish();

                    }, number1);
                }
                else {
                    Toast.makeText(videoactivity.this, "Select a Medium", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.VISIBLE);

        //native ad
        MobileAds.initialize(this);
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();
                        TemplateView template = findViewById(R.id.my_template);
                        template.setVisibility(View.VISIBLE);
                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
}