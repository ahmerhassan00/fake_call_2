package com.example.house.fakecall_2.Audio_calls;

import static com.example.house.fakecall_2.R.raw.burno_voice;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.house.fakecall_2.R;
import com.example.house.fakecall_2.characterscreen;
import com.example.house.fakecall_2.live_chat.chat_activity;

import java.util.Locale;

public class Whatsapp_audiocall extends AppCompatActivity {

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
                Intent i = new Intent(Whatsapp_audiocall.this, audioactivity.class);
                startActivity(i);
                mMediaPlayer.stop();
                mediaPlayer.stop();
                stopTimer();
                finish();
            }
        });

        decline_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Whatsapp_audiocall.this, audioactivity.class);
                startActivity(i);
                mMediaPlayer.stop();
                mediaPlayer.stop();
                stopTimer();
                finish();
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mMediaPlayer.stop();
        mediaPlayer.stop();
    }
}