package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Audio_calls;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.BuildConfig;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class messanger_audio_call extends AppCompatActivity implements MaxAdListener {

    ImageView decline , accept, newDecline, messanger_profile;
    MediaPlayer mMediaPlayer,mediaPlayer;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int timerCount = 0;
    private boolean isTimerRunning = false;
    TextView counterTextView, messanger_txt, messanger_caller;

    String applovin_intrestitial;
    private MaxInterstitialAd maxinterstitialAd;
    private int retryAttempt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger_audio_call);

        decline = findViewById(R.id.decline_messanger);
        accept = findViewById(R.id.answer_messanger);
        newDecline = findViewById(R.id.decline_messanger_two);
        messanger_txt = findViewById(R.id.messanger_txt);
        counterTextView = findViewById(R.id.messanger_counter);
        messanger_caller = findViewById(R.id.messanger_caller_name);
        messanger_profile = findViewById(R.id.profile_image_meassanger);

        mediaPlayer = new MediaPlayer();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.ringtune);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        createInterstitialAd();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String encodedImage = sharedPreferences.getString("image", null);
        String name = sharedPreferences.getString("text", "");

        if (encodedImage != null) {
            byte[] decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            messanger_profile.setImageBitmap(decodedBitmap);
            messanger_caller.setText(name);
        }
        else {
            messanger_profile.setImageResource(R.drawable.char_2);
            messanger_caller.setText("User Name");
        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTimer();
                if (name.equals("Wednesday char")){
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();

                }
                else if (name.equals("Wednesday")) {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();

                }
                else if (name.equals("Adams")){
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                else if (name.equals("Wednesday New")){
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                else {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                decline.setVisibility(View.GONE);
                newDecline.setVisibility(View.VISIBLE);
                accept.setVisibility(View.GONE);
                mMediaPlayer.stop();
                messanger_txt.setVisibility(View.GONE);
                counterTextView.setVisibility(View.VISIBLE);

            }
        });

        newDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxinterstitialAd.isReady()){
                    maxinterstitialAd.showAd();
                    finish();
                }
                mMediaPlayer.stop();
                mediaPlayer.stop();
                stopTimer();

            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxinterstitialAd.isReady()){
                    maxinterstitialAd.showAd();
                }
                mMediaPlayer.stop();
                mediaPlayer.stop();
                stopTimer();

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (maxinterstitialAd.isReady()){
            maxinterstitialAd.showAd();
        }
        mMediaPlayer.stop();
        mediaPlayer.stop();
    }

    private void startTimer() {
        if (!isTimerRunning) {
            isTimerRunning = true;
            runnable = new Runnable() {
                @Override
                public void run() {
                    timerCount++;
                    updateCounterText();
                    handler.postDelayed(this, 1000); // Run every second
                }
            };
            handler.postDelayed(runnable, 1000); // Start the runnable
        }
    }

    private void stopTimer() {
        if (isTimerRunning) {
            isTimerRunning = false;
            handler.removeCallbacks(runnable); // Stop the runnable
        }
    }
    private void updateCounterText() {
        int minutes = timerCount / 60;
        int seconds = timerCount % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        counterTextView.setText(timeFormatted);
    }
    void createInterstitialAd() {

        if (BuildConfig.DEBUG){
            applovin_intrestitial = getString(R.string.app_lovin_interstitial);

        }
        else {
            applovin_intrestitial = getString(R.string.app_lovin_interstitial);
        }
        maxinterstitialAd = new MaxInterstitialAd( applovin_intrestitial, this );
        maxinterstitialAd.setListener( this );

        // Load the first ad
        maxinterstitialAd.loadAd();
    }

    // MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd) {
        // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

        // Reset retry attempt
        retryAttempt = 0;
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {
        // Interstitial ad failed to load
        // AppLovin recommends that you retry with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                maxinterstitialAd.loadAd();
            }
        }, delayMillis );
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {
        // Interstitial ad failed to display. AppLovin recommends that you load the next ad.
        maxinterstitialAd.loadAd();
    }

    @Override
    public void onAdDisplayed(final MaxAd maxAd) {}

    @Override
    public void onAdClicked(final MaxAd maxAd) {}

    @Override
    public void onAdHidden(final MaxAd maxAd)
    {
        // Interstitial ad is hidden. Pre-load the next ad
        maxinterstitialAd.loadAd();
    }

}