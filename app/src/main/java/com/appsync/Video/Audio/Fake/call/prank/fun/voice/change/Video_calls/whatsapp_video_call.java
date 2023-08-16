package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Video_calls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Audio_calls.audioactivity;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.BuildConfig;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import java.util.concurrent.TimeUnit;


public class whatsapp_video_call extends AppCompatActivity implements SurfaceHolder.Callback, MaxAdListener {

//    private static final int REQUEST_CAMERA = 1;
    SurfaceView surfaceView,surfacetwo;
    Camera camera;
    VideoView videoView;
    MediaPlayer mMediaPlayer;
    RelativeLayout relativeLayout, topview;
    TextView caller_name;
    ImageView callend,callAccept,Chatbtn, cancelTwo, profilePic;
//    ProgressDialog mProgressDialog;
    private MaxInterstitialAd maxinterstitialAd;
    String applovin_intrestitial;
    private int retryAttempt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp_video_call);
        videoView = findViewById(R.id.videoView);
        callAccept = findViewById(R.id.answer_video_whatsapp);
        callend = findViewById(R.id.cancel_video_whatsapp);
        Chatbtn = findViewById(R.id.chat_video);
        relativeLayout = findViewById(R.id.bottom_video_items);
        cancelTwo = findViewById(R.id.cancel_video_whatsapp_two);
        profilePic = findViewById(R.id.profile_image_video_whatsapp);
        caller_name = findViewById(R.id.callername_video_whatsapp);
        topview = findViewById(R.id.topItems_vieo_whatsapp);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.setVisibility(View.VISIBLE);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);

        surfacetwo = findViewById(R.id.surfaceTwo);
        SurfaceHolder holdertwo = surfacetwo.getHolder();
        holdertwo.addCallback(this);

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
            caller_name.setText("Wednesday Adams");
        }

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.rainbow_video;
        Uri uri = Uri.parse(videoPath);

        // Create a MediaController for controlling playback
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);



        callAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAccept.setVisibility(View.GONE);
                mMediaPlayer.stop();
                videoView.setVideoURI(uri);
                videoView.start();
                videoView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
                cancelTwo.setVisibility(View.VISIBLE);
                topview.setVisibility(View.GONE);
                surfaceView.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                surfacetwo.setVisibility(View.VISIBLE);
//                mProgressDialog.dismiss();

            }
        });
        callend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxinterstitialAd.isReady()){
                    maxinterstitialAd.showAd();
                    finish();
                }
                videoView.stopPlayback();
                mMediaPlayer.stop();

            }
        });

        cancelTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxinterstitialAd.isReady()){
                    maxinterstitialAd.showAd();
                    finish();
                }
                videoView.stopPlayback();
                mMediaPlayer.stop();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (maxinterstitialAd.isReady()){
            maxinterstitialAd.showAd();
            finish();
        }
        videoView.stopPlayback();
        mMediaPlayer.stop();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {

            Camera open = Camera.open(1);
            this.camera = open;
            this.camera.setParameters(open.getParameters());
            this.camera.setDisplayOrientation(90);
            try {
                this.camera.setPreviewDisplay(surfaceHolder);
                this.camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();


        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

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
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.camera.stopPreview();
        this.camera.release();
        this.camera = null;
    }
    }

