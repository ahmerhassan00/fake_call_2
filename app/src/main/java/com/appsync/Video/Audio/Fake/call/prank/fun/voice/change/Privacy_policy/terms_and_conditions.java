package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Privacy_policy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.BuildConfig;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.welcomescreen;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
import java.util.concurrent.TimeUnit;

public class terms_and_conditions extends AppCompatActivity implements MaxAdListener, MaxAdRevenueListener {

    CheckBox ch1,ch2;
    RelativeLayout btn_continue;
    ProgressDialog mProgressDialog;
    private MaxInterstitialAd interstitialAd_new;
    private Handler handlerRetryAd;
    private int retryAttempt;
    private ViewGroup nativeAdContainerView;
    String applovin_native;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd             loadedNativeAd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        ch1 = findViewById(R.id.chkbx1);
        ch2= findViewById(R.id.chkbx2);
        btn_continue = findViewById(R.id.terms_continue);
        SharedPreferences sharedPreferences = getSharedPreferences("check_entry", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);
        interstitialAd_new = new MaxInterstitialAd("9d02dc5c6e58e5e1", this);
        interstitialAd_new.setListener(this);
        interstitialAd_new.loadAd();

        createNativeAdLoader();

        //camera permission
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission if not granted
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 1);
        }

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getContinue();
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContinue();
            }
        });

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please Wait");
        mProgressDialog.setMessage("Its loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
            }
        },3000);


        if (ch1.isChecked() && ch2.isChecked()){
            btn_continue.setVisibility(View.VISIBLE);
        }

        AppLovinSdk.getInstance(terms_and_conditions.this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(terms_and_conditions.this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads

            }
        });
//        adView = findViewById(R.id.adviewaplovin);
//        adView.loadAd();

        initializeAdNetwork(); // initialize ads only once during the app startup
        createInterstitialAd();

        handlerRetryAd = new Handler();

        // Load your first ad
        loadInterstitialAd();

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitialAd();

                // Set the flag to false to indicate the app has been opened once
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isFirstTime", false);
                editor.apply();

            }
        });
    }

    private void getContinue() {
        if (ch1.isChecked() && ch2.isChecked()){
            btn_continue.setVisibility(View.VISIBLE);
        }
        else{
            btn_continue.setVisibility(View.GONE);
        }
    }

    private void initializeAdNetwork() {
        AppLovinSdk.getInstance(getApplicationContext()).setMediationProvider("max");
        AppLovinSdk.initializeSdk(getApplicationContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
            }
        });
    }

    void createInterstitialAd() {
        interstitialAd_new = new MaxInterstitialAd(getString(R.string.app_lovin_interstitial), this);
        interstitialAd_new.setListener(this);

    }

    // Call this method whenever you want to load a new ad
    private void loadInterstitialAd() {
        interstitialAd_new.loadAd();
    }

    // Call this method to show the ad
    private void showInterstitialAd() {

        interstitialAd_new.showAd();
        Intent i = new Intent(terms_and_conditions.this, welcomescreen.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        handlerRetryAd.removeCallbacksAndMessages(null);
        interstitialAd_new.destroy();
        super.onDestroy();

    }

    @Override
    public void onAdLoaded(MaxAd ad) {
        // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'
        // Reset retry attempt
        retryAttempt = 0;
    }

    @Override
    public void onAdDisplayed(MaxAd ad) {
        // It is called when the ad is shown to the user
    }

    @Override
    public void onAdHidden(MaxAd ad) {
        // User closed the ad. Pre-load the next ad

        loadInterstitialAd();
    }

    @Override
    public void onAdClicked(MaxAd ad) {
        // User clicked on the ad
    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {
        // Interstitial ad failed to load
        // AppLovin recommends that you retry with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        retryAttempt++;
        long delayMillis =
                TimeUnit.SECONDS.toMillis((long) Math.pow(2.0, Math.min(6.0, retryAttempt)));

        Runnable runnableAd = new Runnable() {
            @Override
            public void run() {
                loadInterstitialAd();
            }
        };

        handlerRetryAd.postDelayed(runnableAd, delayMillis);
    }
    private MaxNativeAdView createNativeAdView()
    {
        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder( R.layout.nativead_applovin )
                .setTitleTextViewId( R.id.title_text_view )
                .setBodyTextViewId( R.id.body_text_view )
                .setStarRatingContentViewGroupId( R.id.star_rating_view )
                .setAdvertiserTextViewId( R.id.advertiser_textView )
                .setIconImageViewId( R.id.icon_image_view )
                .setMediaContentViewGroupId( R.id.media_view_container )
                .setOptionsContentViewGroupId( R.id.ad_options_view )
                .setCallToActionButtonId( R.id.cta_button )
                .build();

        return new MaxNativeAdView( binder, terms_and_conditions.this );
    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
        // Interstitial ad failed to display. AppLovin recommends that you load the next ad.
        loadInterstitialAd();
    }
    private void createNativeAdLoader()
    {

        if (BuildConfig.DEBUG){
            applovin_native = getString(R.string.app_lovin_native);
        }
        else {
            applovin_native = getString(R.string.app_lovin_native);
        }
        nativeAdLoader = new MaxNativeAdLoader( applovin_native, this );
        nativeAdLoader.setNativeAdListener( new NativeAdListener() );
        nativeAdLoader.setRevenueListener( this );

        loadNativeAd();
    }

    private void loadNativeAd()
    {
//        nativeAdLoader.loadAd( createNativeAdView() );
        MaxNativeAdView nativeAdView = createNativeAdView();
        nativeAdLoader.loadAd(nativeAdView);
    }

    @Override
    public void onAdRevenuePaid(final MaxAd ad) { }

    public class NativeAdListener extends MaxNativeAdListener
    {
        @Override
        public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd nativeAd)
        {
            // Clean up any pre-existing native ad to prevent memory leaks.
            if ( loadedNativeAd != null )
            {
                nativeAdLoader.destroy( loadedNativeAd );
            }

            // Save ad for cleanup.
            loadedNativeAd = nativeAd;

            FrameLayout nativeAdContainer = findViewById(R.id.nativeAdContainer);
            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(nativeAdView);
            nativeAdContainer.setVisibility(View.VISIBLE);
//            nativeAdContainerView.removeAllViews();
//            nativeAdContainerView.addView( nativeAdView );
        }

        @Override
        public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
        {
            // Native ad load failed.
            // AppLovin recommends retrying with exponentially higher delays up to a maximum delay.
        }

        @Override
        public void onNativeAdClicked(final MaxAd nativeAd) { }
    }

}