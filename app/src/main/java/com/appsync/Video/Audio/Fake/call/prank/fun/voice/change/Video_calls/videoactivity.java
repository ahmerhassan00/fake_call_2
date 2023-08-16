package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Video_calls;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Audio_calls.audioactivity;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.BuildConfig;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.home;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
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

public class videoactivity extends AppCompatActivity implements MaxAdViewAdListener, MaxAdRevenueListener {


    int count = 0;
    TextView time,medium;
    int number ,number1;
    private InterstitialAd fb_interstitialAd;
    String fb_intrestitia_id,applovin_banner;
    ProgressDialog mProgressDialog;
    ImageView back,timeNext,timeBack,SrcNext,SrcBack,startProcess;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd             loadedNativeAd;
    String applovin_native;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoactivity);
        back = findViewById(R.id.backbtn);
        timeNext = findViewById(R.id.video_time_next);
        timeBack = findViewById(R.id.video_time_back);
        SrcNext = findViewById(R.id.video_medium_next);
        SrcBack = findViewById(R.id.video_medium_back);
        time = findViewById(R.id.video_txt2);
        medium = findViewById(R.id.video_txt3);
        startProcess = findViewById(R.id.start_video_call);
        MaxAdView adView1;
        String[] toggleOptions = {"Whatsapp", "Messanger"};
        final int[] currentIndex = {0};
        String[] toggleTime = {"Now", "10s", "20s", "30s", "1m", "5m"};
        final int[] currentIndextime = {0};


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please Wait");
        mProgressDialog.setMessage("Its loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        createNativeAdLoader();

        //applovin banner
        createBannerAd();
        adView1 = findViewById( R.id.adView );
        adView1.loadAd();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
            }
        },3000);

        AudienceNetworkAds.initialize(this);
        if (BuildConfig.DEBUG){
            fb_intrestitia_id = getString(R.string.facebook_Interstitial_test);
        }
        else {
            fb_intrestitia_id = getString(R.string.facebook_Interstitial_live);
        }
        fb_interstitialAd = new InterstitialAd(this, fb_intrestitia_id);

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
                Intent i = new Intent(videoactivity.this, home.class);
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
        fb_interstitialAd.loadAd(
                fb_interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());


        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads

            }
        } );
//        loadNativeAd();
//        createBannerAd();
//        createNativeAd();

        //camera permission
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission if not granted
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 1);
        }
        timeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setText(toggleTime[currentIndextime[0]]);

                currentIndextime[0] = (currentIndextime[0] + 1) % toggleTime.length;
            }
        });
        timeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.setText(toggleTime[currentIndextime[0]]);

                currentIndextime[0] = (currentIndextime[0] + 1) % toggleTime.length;
            }
        });

        SrcNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medium.setText(toggleOptions[currentIndex[0]]);

                currentIndex[0] = (currentIndex[0] + 1) % toggleOptions.length;

            }
        });
        SrcBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medium.setText(toggleOptions[currentIndex[0]]);

                currentIndex[0] = (currentIndex[0] + 1) % toggleOptions.length;

            }
        });

        startProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (medium.getText().equals("Messanger")){

                    if (time.getText().toString().equals("Now")){
                        number = 0;
                    }
                    else if (time.getText().toString().equals("10s")) {
                        number = 10000;
                        Toast.makeText(videoactivity.this, "Call will start after 10 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    }
                    else if (time.getText().toString().equals("20s")) {
                        number = 20000;
                        Toast.makeText(videoactivity.this, "Call will start after 20 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    }
                    else if (time.getText().toString().equals("30s")) {
                        number = 30000;
                        Toast.makeText(videoactivity.this, "Call will start after 30 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    }
                    else if (time.getText().toString().equals("1m")) {
                        number = 60000;
                        Toast.makeText(videoactivity.this, "Call will start after 1 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    }
                    else if (time.getText().toString().equals("5m")) {
                        number = 300000;
                        Toast.makeText(videoactivity.this, "Call will start after 5 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        Intent i = new Intent(videoactivity.this, messanger_video_call.class);
                        startActivity(i);
                        finish();

                    },number);

                } else if (medium.getText().equals("Whatsapp")) {

                    if (time.getText().toString().equals("Now")) {

                        number1 = 0;
                    } else if (time.getText().toString().equals("10s")) {
                        number1 = 10000;
                        Toast.makeText(videoactivity.this, "Call will start after 10 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    } else if (time.getText().toString().equals("20s")) {
                        number1 = 20000;
                        Toast.makeText(videoactivity.this, "Call will start after 20 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    } else if (time.getText().toString().equals("30s")) {
                        number1 = 30000;
                        Toast.makeText(videoactivity.this, "Call will start after 30 seconds", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);
                    } else if (time.getText().toString().equals("1m")) {
                        number1 = 60000;
                        Toast.makeText(videoactivity.this, "Call will start after 1 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    } else if (time.getText().toString().equals("5m")) {
                        number1 = 300000;
                        Toast.makeText(videoactivity.this, "Call will start after 5 Minute", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(videoactivity.this, home.class);
                        startActivity(i);

                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        Intent i = new Intent(videoactivity.this, whatsapp_video_call.class);
                        startActivity(i);
                        finish();

                    }, number1);
                }
                else {
                    Toast.makeText(videoactivity.this, "Select a Medium", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                fb_interstitialAd.show();
            }
        });
//        AdView adView = new AdView(this);
//
//        adView.setAdSize(AdSize.BANNER);
//
//        adView.setAdUnitId(getString(R.string.admob_banner_test));
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        mAdView.setVisibility(View.VISIBLE);
//
//        //native ad
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

    protected void onRestart() {
        super.onRestart();

        fb_interstitialAd.loadAd();
        mProgressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
            }
        },1800);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mProgressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fb_interstitialAd.show();
    }

    //applovin Banner----------------------------------------------------
//    void createBannerAd() {
//        aplovinBanner = new MaxAdView( getString(R.string.app_lovin_Banner), this );
//        aplovinBanner.setListener( this );
//
//        // Stretch to the width of the screen for banners to be fully functional
//        int width = ViewGroup.LayoutParams.MATCH_PARENT;
//
//        // Banner height on phones and tablets is 50 and 90, respectively
//        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );
//
//        aplovinBanner.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );
//
////        // Set background or background color for banners to be fully functional
////        adView.setBackgroundColor( ... );
//        int marginTopPx = getResources().getDimensionPixelSize(R.dimen.margin_top);
//
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, heightPx);
//        layoutParams.setMargins(0, marginTopPx, 0, 0); // Set margins (left, top, right, bottom)
//        aplovinBanner.setLayoutParams(layoutParams);
//
//        ViewGroup rootView = findViewById( android.R.id.content );
//        rootView.addView( aplovinBanner );
//
//        // Load the ad
//        aplovinBanner.loadAd();
//    }



    ///////--------------AppLovin Native Ads .............................
//    @Override
//    public void onAdExpanded(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdCollapsed(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdLoaded(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdDisplayed(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdHidden(MaxAd maxAd) {
//
//        Toast.makeText(this, ""+maxAd, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onAdClicked(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdLoadFailed(String s, MaxError maxError) {
//        Toast.makeText(this, "Banner load: "+maxError.getMessage(), Toast.LENGTH_SHORT).show();
//        Log.d("lovin_banner","Banner_lovin"+maxError.getMessage());
//
//    }
//
//    @Override
//    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
//        Toast.makeText(this, "Banner display: "+maxError.getMessage(), Toast.LENGTH_SHORT).show();
//
//    }
//
//    void createNativeAd()
//    {
//        FrameLayout nativeAdContainer = findViewById( R.id.native_ad_layout );
//
//        nativeAdLoader = new MaxNativeAdLoader( getString(R.string.app_lovin_medium_native), this );
//        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
//        {
//            @Override
//            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
//            {
//                // Clean up any pre-existing native ad to prevent memory leaks.
//                if ( nativeAd != null )
//                {
//                    nativeAdLoader.destroy( nativeAd );
////                    float aspectRatio = nativeAd.getMediaContentAspectRatio();
//                }
//
//                // Save ad for cleanup.
//                nativeAd = ad;
//
//                // Add ad view to view.
//                nativeAdContainer.removeAllViews();
//                nativeAdContainer.addView( nativeAdView );
//            }
//
//            @Override
//            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
//            {
//                // We recommend retrying with exponentially higher delays up to a maximum delay
//                Toast.makeText(videoactivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNativeAdClicked(final MaxAd ad)
//            {
//                // Optional click callback
//            }
//        } );
//
//        nativeAdLoader.loadAd();
//    }
//    private void loadNativeAd() {
//        ViewGroup adContainer = findViewById(R.id.adContainer);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        ViewGroup adView = (ViewGroup) inflater.inflate(R.layout.custom_native_ad_layout, adContainer, false);
//
//        AppLovinNativeAd.loadPreloaded(adView, this, new AppLovinNativeAdLoadListener() {
//            @Override
//            public void onNativeAdLoaded(AppLovinNativeAd nativeAd) {
//                TextView titleView = adView.findViewById(R.id.titleTextView);
//                ImageView imageView = adView.findViewById(R.id.imageView);
//                TextView descriptionView = adView.findViewById(R.id.descriptionTextView);
//                Button ctaButton = adView.findViewById(R.id.ctaButton);
//
//                titleView.setText(nativeAd.getTitle());
//                // Load ad image using your preferred image loading library
//                // Example: Picasso.get().load(nativeAd.getImageUrl()).into(imageView);
//                descriptionView.setText(nativeAd.getDescription());
//                ctaButton.setText(nativeAd.getCtaText());
//
//                // Register click listener and track ad interaction
//                ctaButton.setOnClickListener(v -> {
//                    nativeAd.trackClick();
//                    // Handle click action
//                });
//
//                // Attach the ad view to the container
//                adContainer.addView(adView);
//            }
//
//            @Override
//            public void onNativeAdLoadFailed(AppLovinError appLovinError) {
//
//            }
//
//        });
//    }

    void createBannerAd()
    {

        if (BuildConfig.DEBUG){
            applovin_banner = getString(R.string.app_lovin_Banner);
        }
        else {
            applovin_banner = getString(R.string.app_lovin_Banner);
        }
        MaxAdView adView1 = new MaxAdView(applovin_banner, this);
        adView1.setListener( this );

        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView1.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );

        // Set background or background color for banners to be fully functional

        ViewGroup rootView = findViewById( android.R.id.content );
        rootView.addView( adView1 );

        // Load the ad
        adView1.loadAd();
    }

    // MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd) {}

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {}

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {}

    @Override
    public void onAdClicked(final MaxAd maxAd) {}

    @Override
    public void onAdExpanded(final MaxAd maxAd) {}

    @Override
    public void onAdCollapsed(final MaxAd maxAd) {}

    @Override
    public void onAdDisplayed(final MaxAd maxAd) { /* DO NOT USE - THIS IS RESERVED FOR FULLSCREEN ADS ONLY AND WILL BE REMOVED IN A FUTURE SDK RELEASE */ }

    @Override
    public void onAdHidden(final MaxAd maxAd) { /* DO NOT USE - THIS IS RESERVED FOR FULLSCREEN ADS ONLY AND WILL BE REMOVED IN A FUTURE SDK RELEASE */ }

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

        return new MaxNativeAdView( binder, videoactivity.this );
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