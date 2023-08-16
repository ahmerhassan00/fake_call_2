package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Privacy_policy.terms_and_conditions;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.concurrent.TimeUnit;

public class welcomescreen extends AppCompatActivity implements MaxAdListener, MaxAdRevenueListener {

    //    private MaxAdView adView;

    private boolean initialLayoutComplete = false;
    RelativeLayout relativeLayout;
    private MaxInterstitialAd maxinterstitialAd;
    private int retryAttempt;
    ProgressDialog mProgressDialog;
    String applovin_intrestitial;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd             loadedNativeAd;
    String applovin_native;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);
        relativeLayout = findViewById(R.id.continuebtn);

        createInterstitialAd();

        createNativeAdLoader();

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
        }, 2000);



        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (maxinterstitialAd.isReady()) {
                    maxinterstitialAd.showAd();
                    Intent i = new Intent(welcomescreen.this, home.class);
                    startActivity(i);
                }


            }
        });

//        AdView adView = new AdView(this);
//
//        adView.setAdSize(AdSize.BANNER);
//
//        adView.setAdUnitId(getString(R.string.admob_banner_test));
//
////        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
////        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
////            @Override
////            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
////            {
////                // AppLovin SDK is initialized, start loading ads
////                createBannerAd();
////            }
////        } );
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
////        native ad
//        MobileAds.initialize(this);
//        AdLoader adLoader = new AdLoader.Builder(this, getString(R.string.admob_Native_test))
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        NativeTemplateStyle styles = new
//                                NativeTemplateStyle.Builder().build();
//                        TemplateView template = findViewById(R.id.my_template);
//                        template.setVisibility(View.VISIBLE);
//                        template.setStyles(styles);
//                        template.setNativeAd(nativeAd);
//                    }
//                })
//                .build();
//
//        adLoader.loadAd(new AdRequest.Builder().build());
    }

    void createInterstitialAd() {

        if (BuildConfig.DEBUG) {
            applovin_intrestitial = getString(R.string.app_lovin_interstitial);

        } else {
            applovin_intrestitial = getString(R.string.app_lovin_interstitial);
        }
        maxinterstitialAd = new MaxInterstitialAd(applovin_intrestitial, this);
        maxinterstitialAd.setListener(this);

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
        long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                maxinterstitialAd.loadAd();
            }
        }, delayMillis);
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {
        // Interstitial ad failed to display. AppLovin recommends that you load the next ad.
        maxinterstitialAd.loadAd();
    }

    @Override
    public void onAdDisplayed(final MaxAd maxAd) {
    }

    @Override
    public void onAdClicked(final MaxAd maxAd) {
    }

    @Override
    public void onAdHidden(final MaxAd maxAd) {
        // Interstitial ad is hidden. Pre-load the next ad
        maxinterstitialAd.loadAd();
    }

    protected void onRestart() {
        super.onRestart();

        maxinterstitialAd.loadAd();
        createInterstitialAd();

        mProgressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
            }
        }, 2000);

    }


    @Override
    protected void onPause() {
        super.onPause();
        mProgressDialog.dismiss();
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

        return new MaxNativeAdView( binder, welcomescreen.this );
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
        nativeAdLoader.setNativeAdListener(new NativeAdListener());
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

    private class NativeAdListener extends MaxNativeAdListener
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