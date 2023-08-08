package com.example.house.fakecall_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class audioactivity extends AppCompatActivity {

    AdView mAdView;
    int count = 0;
    TextView time,medium;
    String number;
    ImageView back, timeNext,timeBack,SrcNext,SrcBack,startProcess;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioactivity);
        back = findViewById(R.id.backbtn);
        timeNext = findViewById(R.id.timerNext);
        timeBack = findViewById(R.id.timerBack);
        SrcNext = findViewById(R.id.srcNext);
        SrcBack = findViewById(R.id.srcBack);
        time = findViewById(R.id.audio_txt2);
        medium = findViewById(R.id.audio_txt3);
        startProcess = findViewById(R.id.startCall);
        String[] toggleOptions = {"Whatsapp", "Messanger"};
        final int[] currentIndex = {0};

        timeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 10) {
                    count++;
                    number = String.valueOf(count);
                    time.setText(number);
                }
            }
        });
        timeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 1) {
                    count--;
                    number = String.valueOf(count);
                    time.setText(number);
                }
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

                    String zeros = "000";
                    int sec = count;

                    String num = sec+zeros;
                    int delayTime = Integer.parseInt(num);

                    if (sec>=2){
                        Intent i = new Intent(audioactivity.this,home.class);
                        startActivity(i);
                        finish();
                    }

                    Toast.makeText(audioactivity.this, "Call will start after "+ sec+" seconds", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        Intent i = new Intent(audioactivity.this, messanger_audio_call.class);
                        startActivity(i);
                        finish();

                    },delayTime);

                } else if (medium.getText().equals("Whatsapp")) {

                    String zeros = "000";
                    int sec = count;

                    String num = sec+zeros;
                    int delayTime = Integer.parseInt(num);

                    Toast.makeText(audioactivity.this, "Call will start after "+ sec+" seconds", Toast.LENGTH_SHORT).show();

                    if (sec>=2){
                        Intent i = new Intent(audioactivity.this,home.class);
                        startActivity(i);
                        finish();
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        Intent i = new Intent(audioactivity.this, Whatsapp_audiocall.class);
                        startActivity(i);
                        finish();

                    },delayTime);

                }
                else {
                    Toast.makeText(audioactivity.this, "Select a Medium", Toast.LENGTH_SHORT).show();
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