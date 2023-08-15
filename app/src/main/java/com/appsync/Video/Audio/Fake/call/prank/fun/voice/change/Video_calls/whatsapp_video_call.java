package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Video_calls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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

import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Audio_calls.audioactivity;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.BuildConfig;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;


public class whatsapp_video_call extends AppCompatActivity implements SurfaceHolder.Callback {

//    private static final int REQUEST_CAMERA = 1;

    SurfaceView surfaceView,surfacetwo;
    Camera camera;
    VideoView videoView;
    MediaPlayer mMediaPlayer;
    RelativeLayout relativeLayout, topview;
    TextView caller_name;
    ImageView callend,callAccept,Chatbtn, cancelTwo, profilePic;
    private InterstitialAd interstitialAd;
    String fb_intrestitia_id;

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

        AudienceNetworkAds.initialize(this);
        if (BuildConfig.DEBUG){
            fb_intrestitia_id = getString(R.string.facebook_Interstitial_test);
        }
        else {
            fb_intrestitia_id = getString(R.string.facebook_Interstitial_live);
        }
        interstitialAd = new InterstitialAd(this, fb_intrestitia_id);

        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.");
                Intent i = new Intent(whatsapp_video_call.this, audioactivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed

                // Show the ad

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback

            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

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

            }
        });
        callend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialAd.show();
                videoView.stopPlayback();
                mMediaPlayer.stop();

            }
        });

        cancelTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialAd.show();
                videoView.stopPlayback();
                mMediaPlayer.stop();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.camera.stopPreview();
        this.camera.release();
        this.camera = null;
    }
    }
    //    private void openCameraApp() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent, REQUEST_CAMERA);
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CAMERA) {
//            // Handle the result if needed
//
//        }

