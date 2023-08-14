package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
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

import java.io.ByteArrayOutputStream;

public class characterscreen extends AppCompatActivity {

    AdView mAdView;
    ImageView back,img1,img2,img3,img4;
    Button btn1,btn2,btn3,btn4;
    String btn_txt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characterscreen);

        back = findViewById(R.id.back);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img1.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn1.getText().toString();
                saveImageToSharedPreferences(imageBitmap,btn_txt);
                Toast.makeText(characterscreen.this, btn1.getText().toString()+" Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img2.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn2.getText().toString();
                saveImageToSharedPreferences(imageBitmap,btn_txt);
                Toast.makeText(characterscreen.this, btn2.getText().toString()+" Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img3.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn3.getText().toString();
                saveImageToSharedPreferences(imageBitmap,btn_txt);
                Toast.makeText(characterscreen.this, btn3.getText().toString()+" Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img4.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn4.getText().toString();
                saveImageToSharedPreferences(imageBitmap,btn_txt);
                Toast.makeText(characterscreen.this, btn4.getText().toString()+" Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);
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

        adView.setAdUnitId(getString(R.string.admob_banner_test));
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
        AdLoader adLoader = new AdLoader.Builder(this, getString(R.string.admob_Native_test))
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
    private
    void saveImageToSharedPreferences(Bitmap imageBitmap , String btn_txtt) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", encodedImage);
        editor.putString("text", btn_txtt);
        editor.apply();
    }

}