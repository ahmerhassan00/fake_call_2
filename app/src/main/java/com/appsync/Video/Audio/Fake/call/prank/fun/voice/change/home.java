package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Audio_calls.audioactivity;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Video_calls.videoactivity;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.concurrent.TimeUnit;

public class home extends AppCompatActivity implements MaxAdListener {
    AdView mAdView;
    RelativeLayout video,audio,settings;
    RelativeLayout charr;
    String bannerID,nativeId, applovin_intrestitial, admob_interstitial;
    private InterstitialAd mInterstitialAd;
    String fb_interestial;
    private MaxInterstitialAd maxinterstitialAd;
    private int retryAttempt;
    ProgressDialog mProgressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        video = findViewById(R.id.videobtn);
        audio = findViewById(R.id.audiobtn);
        settings = findViewById(R.id.settingsbtn);
        charr = findViewById(R.id.selectcharacter);

        createInterstitialAd();
        admob_interstitial();

        AudienceNetworkAds.initialize(this);

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

        //facebook intrestitial
        if (BuildConfig.DEBUG){
            fb_interestial = getString(R.string.facebook_Interstitial_test);
        }
        else {
            fb_interestial = getString(R.string.facebook_Interstitial_live);
        }
        com.facebook.ads.InterstitialAd interstitialAd = new com.facebook.ads.InterstitialAd(this, fb_interestial);
        // Create listeners for the Interstitial Ad
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
                Intent i = new Intent(home.this, settingsactivity.class);
                startActivity(i);
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
        charr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd!=null){
                    mInterstitialAd.show(home.this);
                }

            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (maxinterstitialAd.isReady()){
                    maxinterstitialAd.showAd();
                    Intent i = new Intent(home.this, videoactivity.class);
                    startActivity(i);
                }

            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maxinterstitialAd.isReady()) {
                    maxinterstitialAd.showAd();
                    Intent i = new Intent(home.this, audioactivity.class);
                    startActivity(i);
                }
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }

            }
        });


        //admob Banner
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        if (BuildConfig.DEBUG){
            bannerID = getString(R.string.admob_banner_test);
            admob_interstitial = getString(R.string.admob_Interstitial_test);
        }
        else {
            bannerID = getString(R.string.admob_banner_live);
            admob_interstitial = getString(R.string.admob_Interstitial_live);
        }
        adView.setAdUnitId(bannerID);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //native ad admob
        if (BuildConfig.DEBUG){
            nativeId = getString(R.string.admob_Native_test);

        }
        else {
            nativeId = getString(R.string.admob_Native_live);
        }
        MobileAds.initialize(this);
        AdLoader adLoader = new AdLoader.Builder(this, nativeId)
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

    public void admob_interstitial(){

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        if (BuildConfig.DEBUG){
            admob_interstitial = getString(R.string.admob_Interstitial_test);

        }
        else {
            admob_interstitial = getString(R.string.admob_Interstitial_live);
        }
        InterstitialAd.load(this,admob_interstitial , adRequest,
                new InterstitialAdLoadCallback() {

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        if (mInterstitialAd != null) {
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {

                                    Intent i = new Intent(home.this, characterscreen.class);
                                    startActivity(i);
                                    mInterstitialAd = null;
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                    // Handle the case when the ad fails to display
                                }

                                // Override other callback methods if needed
                            });

                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    //applovin Intrestitial
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
    protected void onRestart() {
        super.onRestart();

        maxinterstitialAd.loadAd();
        createInterstitialAd();
        admob_interstitial();
        mProgressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
            }
        },2000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mProgressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(home.this, Exit_screen.class);
        startActivity(i);
    }
}