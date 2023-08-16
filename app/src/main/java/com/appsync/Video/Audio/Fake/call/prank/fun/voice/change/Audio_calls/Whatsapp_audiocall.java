package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Audio_calls;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.BuildConfig;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.live_chat.chat_activity;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Whatsapp_audiocall extends AppCompatActivity implements MaxAdListener {

    ImageView answer_call,chats, decline_call, dec_two, micON,micOff,speakerOn,speakerOff, profilePic;
    private RelativeLayout swipeContainer;
    private float initialX,initialY;
    private float originalY;
    private int maxMoveDistance = 30;
    private TextView counterTextView, whatsapptxt, caller_name;

    private Handler handler = new Handler();
    private Runnable runnable;
    private int timerCount = 0;
    private boolean isTimerRunning = false;
    MediaPlayer mMediaPlayer,mediaPlayer;
//    ProgressDialog mProgressDialog;
    String applovin_intrestitial;
    private MaxInterstitialAd maxinterstitialAd;
    private int retryAttempt;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_audiocall);

        answer_call = findViewById(R.id.answer);
        swipeContainer = findViewById(R.id.lastItems);
        decline_call = findViewById(R.id.decline);
        counterTextView = findViewById(R.id.wA_timer);
        whatsapptxt = findViewById(R.id.wA_txt);
        dec_two = findViewById(R.id.decline_two);
        speakerOn = findViewById(R.id.speaker);
        speakerOff = findViewById(R.id.mute);
        micON = findViewById(R.id.mic_off);
        micOff = findViewById(R.id.mic_on);
        chats = findViewById(R.id.chat);
        profilePic = findViewById(R.id.profile_image);
        caller_name = findViewById(R.id.callername);

        mediaPlayer = new MediaPlayer();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.ringtune);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        createInterstitialAd();

//        mProgressDialog = new ProgressDialog(this);
//        mProgressDialog.setTitle("Please Wait");
//        mProgressDialog.setMessage("Its loading...");
//        mProgressDialog.setCancelable(false);
//        mProgressDialog.show();
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mProgressDialog.dismiss();
//            }
//        },3000);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String encodedImage = sharedPreferences.getString("image", null);
        String name = sharedPreferences.getString("text", "");

        if (encodedImage != null) {
            byte[] decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            profilePic.setImageBitmap(decodedBitmap);
            caller_name.setText(name);
        }
        else {
            profilePic.setImageResource(R.drawable.char_2);
            caller_name.setText("User Name");
        }

        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Whatsapp_audiocall.this, chat_activity.class);
                mMediaPlayer.stop();
                startActivity(i);
                finish();
            }
        });

        micON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                micON.setVisibility(View.GONE);
                micOff.setVisibility(View.VISIBLE);
            }
        });

        micOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                micON.setVisibility(View.VISIBLE);
                micOff.setVisibility(View.GONE);
            }
        });

        speakerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakerOn.setVisibility(View.VISIBLE);
                speakerOff.setVisibility(View.GONE);
            }
        });

        speakerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakerOn.setVisibility(View.GONE);
                speakerOff.setVisibility(View.VISIBLE);
            }
        });
        answer_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.equals("Wednesday char")){

                    mediaPlayer = MediaPlayer.create(Whatsapp_audiocall.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                } else if (name.equals("Wednesday")) {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(Whatsapp_audiocall.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();

                }
                else if (name.equals("Adams")){

                    mediaPlayer = MediaPlayer.create(Whatsapp_audiocall.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                else if (name.equals("Wednesday New")){

                    mediaPlayer = MediaPlayer.create(Whatsapp_audiocall.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                else {
                    mediaPlayer = MediaPlayer.create(Whatsapp_audiocall.this, R.raw.burno_voice);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }


                mMediaPlayer.stop();
                whatsapptxt.setVisibility(View.GONE);
                counterTextView.setVisibility(View.VISIBLE);
                answer_call.setVisibility(View.GONE);
                decline_call.setVisibility(View.GONE);
                dec_two.setVisibility(View.VISIBLE);
                speakerOn.setVisibility(View.VISIBLE);
                micOff.setVisibility(View.VISIBLE);
                chats.setVisibility(View.GONE);
                startTimer();

            }
        });
        dec_two.setOnClickListener(new View.OnClickListener() {
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

        decline_call.setOnClickListener(new View.OnClickListener() {
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


//
//        decline_call.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        initialY = motionEvent.getRawY();
//                        originalY = view.getY() - 100;
//                        return true;
//
//                    case MotionEvent.ACTION_MOVE:
//                        float offsetY = motionEvent.getRawY() - initialY;
//                        offsetY = Math.max(-maxMoveDistance, Math.min(maxMoveDistance, offsetY)); // Clamp within the range
//                        float currentY = motionEvent.getY();
//                        float distanceY = currentY - initialY;
//                        view.setY(originalY + offsetY);
//                        if (offsetY > 80 && distanceY > 80) {
//                            Toast.makeText(Whatsapp_audiocall.this, "Declined", Toast.LENGTH_SHORT).show();
//                        }
//
//                        return true;
//
//                    case MotionEvent.ACTION_UP:
//                        // Animate button back to original position
//                        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "y", originalY);
//                        animatorY.setDuration(300);
//                        animatorY.start();
//                        return true;
//                }
//                return false;
//            }
//        });
//
//        answer_call.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        initialX = motionEvent.getX();
//                        return true;
//                    case MotionEvent.ACTION_MOVE:
//                        float currentX = motionEvent.getX();
//                        float distanceX = currentX - initialX;
//                        if (distanceX > 80) {
//                            Toast.makeText(Whatsapp_audiocall.this, "Answered", Toast.LENGTH_SHORT).show();
//                        }
//                        return true;
//                }
//                return false;
//            }
//        });
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (maxinterstitialAd.isReady()){
            maxinterstitialAd.showAd();
            finish();
        }
        mMediaPlayer.stop();
        mediaPlayer.stop();
    }
}