package com.example.house.fakecall_2;

import static com.example.house.fakecall_2.R.raw.im_iron_man;
import static com.example.house.fakecall_2.R.raw.tonystart_dialog;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class messanger_audio_call extends AppCompatActivity {

    ImageView decline , accept, newDecline, messanger_profile;
    MediaPlayer mMediaPlayer,mediaPlayer;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int timerCount = 0;
    private boolean isTimerRunning = false;
    TextView counterTextView, messanger_txt, messanger_caller;

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

                if (name.equals("Char 1")){
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, tonystart_dialog);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                } else if (name.equals("Char 2")) {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, im_iron_man);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();

                }
                else if (name.equals("Char 3")){
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, tonystart_dialog);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                else if (name.equals("Char 4")){
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer = MediaPlayer.create(messanger_audio_call.this, im_iron_man);
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
//                mediaPlayer = new MediaPlayer();
//                mediaPlayer = MediaPlayer.create(messanger_audio_call.this, tonystart_dialog);
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setLooping(true);
//                mediaPlayer.start();
                startTimer();


            }
        });

        newDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(messanger_audio_call.this, audioactivity.class);
                startActivity(i);
                mMediaPlayer.stop();
                mediaPlayer.stop();
                stopTimer();
                finish();
            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(messanger_audio_call.this, audioactivity.class);
                startActivity(i);
                mMediaPlayer.stop();
                mediaPlayer.stop();
                stopTimer();
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
}