package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.live_chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class chat_activity extends AppCompatActivity implements MaxAdViewAdListener {
    private EditText userInputEditText;
    private RecyclerView recyclerView;
    private Runnable runnable;
    ScrollView scrollView;
    int scrollY = 0;
    private chat_adapter chatAdapter;
    private List<chatMessageModel> chatMessages;
    private List<String> botResponses;
    Handler handler = new Handler();
    private MaxAdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        userInputEditText = findViewById(R.id.txt_chat);
        recyclerView = findViewById(R.id.recyclerChats);
        scrollView = findViewById(R.id.scroll);

        // Set up RecyclerView with adapter
        chatMessages = new ArrayList<>();
        chatAdapter = new chat_adapter(chatMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        // Add some predefined bot responses
        botResponses = new ArrayList<>();
        botResponses.add("Hello!");
        botResponses.add("How can I assist you?");
        botResponses.add("Nice to meet you!");


        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads

            }
        } );
                createBannerAd();
        runnable = new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo(0, scrollY);
                scrollY += 10;
                handler.postDelayed(runnable, 100);
            }
        };

        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                startAutoScroll();
            }
        });
        RelativeLayout sendButton = findViewById(R.id.btm_relative);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMessage = userInputEditText.getText().toString();
                addUserMessage(userMessage);
                generateAndAddBotResponse();
                userInputEditText.setText("");
            }
        });
    }
    private void addUserMessage(String message) {
        chatMessageModel userMessage = new chatMessageModel(message, true);
        chatMessages.add(userMessage);
        chatAdapter.notifyDataSetChanged();
    }

    private void generateAndAddBotResponse() {
        String botResponse = getRandomBotResponse();
        chatMessageModel botMessage = new chatMessageModel(botResponse, false);
        chatMessages.add(botMessage);
        chatAdapter.notifyDataSetChanged();
    }

    private String getRandomBotResponse() {
        int randomIndex = new Random().nextInt(botResponses.size());
        return botResponses.get(randomIndex);
    }

    private void startAutoScroll() {
        handler.postDelayed(runnable, 500);
    }
    void createBannerAd()
    {
        adView = new MaxAdView( getString(R.string.app_lovin_Banner), this );
        adView.setListener( this );

        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );

//        // Set background or background color for banners to be fully functional
//        adView.setBackgroundColor( ... );

        ViewGroup rootView = findViewById( android.R.id.content );
        rootView.addView( adView );

        // Load the ad
        adView.loadAd();
    }


    @Override
    public void onAdExpanded(MaxAd maxAd) {

    }

    @Override
    public void onAdCollapsed(MaxAd maxAd) {

    }

    @Override
    public void onAdLoaded(MaxAd maxAd) {

    }

    @Override
    public void onAdDisplayed(MaxAd maxAd) {

    }

    @Override
    public void onAdHidden(MaxAd maxAd) {

    }

    @Override
    public void onAdClicked(MaxAd maxAd) {

    }

    @Override
    public void onAdLoadFailed(String s, MaxError maxError) {
        Toast.makeText(this, "Banner load: "+maxError.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
        Toast.makeText(this, "Banner display: "+maxError.getMessage(), Toast.LENGTH_SHORT).show();

    }
}