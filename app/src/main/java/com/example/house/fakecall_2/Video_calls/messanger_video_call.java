package com.example.house.fakecall_2.Video_calls;

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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.house.fakecall_2.R;

public class messanger_video_call extends AppCompatActivity implements SurfaceHolder.Callback {

    SurfaceView surfaceView,surfacetwo;
    Camera camera;
    VideoView videoView;
    MediaPlayer mMediaPlayer;
    RelativeLayout relativeLayout, topitems;
    TextView caller_name;
    ImageView callend,callAccept,Chatbtn, cancelTwo, profilePic;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger_video_call);

        videoView = findViewById(R.id.videoView_messanger);
        callAccept = findViewById(R.id.answer_messanger_video);
        callend = findViewById(R.id.decline_messanger_video);
        topitems = findViewById(R.id.topItems_vieo_whatsapp_messanger);

        relativeLayout = findViewById(R.id.bottomItems_messanger_video);
        cancelTwo = findViewById(R.id.cancel_video_messanger_two);
        profilePic = findViewById(R.id.profile_image_video_messanger);
        caller_name = findViewById(R.id.callername_video_messanger);

        surfaceView = findViewById(R.id.surfaceView_messanger);
        surfaceView.setVisibility(View.VISIBLE);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);

        surfacetwo = findViewById(R.id.surfaceTwo_messanger);
        SurfaceHolder holdertwo = surfacetwo.getHolder();
        holdertwo.addCallback(this);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.ringtune);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

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
                topitems.setVisibility(View.GONE);
                surfaceView.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                surfacetwo.setVisibility(View.VISIBLE);

            }
        });
        callend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(messanger_video_call.this, videoactivity.class);
                startActivity(i);
                videoView.stopPlayback();
                mMediaPlayer.stop();
                finish();
            }
        });

        cancelTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(messanger_video_call.this, videoactivity.class);
                startActivity(i);
                videoView.stopPlayback();
                mMediaPlayer.stop();
                finish();
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