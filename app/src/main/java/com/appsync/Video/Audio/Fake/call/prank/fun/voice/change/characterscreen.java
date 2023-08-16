package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.Video_calls.videoactivity;
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

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

public class characterscreen extends AppCompatActivity implements MaxAdListener, MaxAdViewAdListener, MaxAdRevenueListener {

    AdView mAdView;
    ImageView back, img1, img2, img3, img4;
    Button btn1, btn2, btn3, btn4;
    String btn_txt, applovin_intrestitial, applovin_banner;
    private MaxInterstitialAd maxinterstitialAd;
    private int retryAttempt;
    ProgressDialog mProgressDialog;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd             loadedNativeAd;
    String applovin_native;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characterscreen);

        back = findViewById(R.id.back);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        createInterstitialAd();
        createNativeAdLoader();

        //applovin banner
        createBannerAd();
        MaxAdView adView1 = findViewById(R.id.adView);
        adView1.loadAd();

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


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img1.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn1.getText().toString();
                saveImageToSharedPreferences(imageBitmap, btn_txt);
                Toast.makeText(characterscreen.this, btn1.getText().toString() + " Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img2.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn2.getText().toString();
                saveImageToSharedPreferences(imageBitmap, btn_txt);
                Toast.makeText(characterscreen.this, btn2.getText().toString() + " Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img3.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn3.getText().toString();
                saveImageToSharedPreferences(imageBitmap, btn_txt);
                Toast.makeText(characterscreen.this, btn3.getText().toString() + " Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) img4.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                btn_txt = btn4.getText().toString();
                saveImageToSharedPreferences(imageBitmap, btn_txt);
                Toast.makeText(characterscreen.this, btn4.getText().toString() + " Selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(characterscreen.this, home.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                if (maxinterstitialAd.isReady()) {
                    maxinterstitialAd.showAd();
                    finish();
                }
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

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
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

    private void saveImageToSharedPreferences(Bitmap imageBitmap, String btn_txtt) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", encodedImage);
        editor.putString("text", btn_txtt);
        editor.apply();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (maxinterstitialAd.isReady()) {
            maxinterstitialAd.showAd();
            finish();
        }
    }

    void createBannerAd() {

        if (BuildConfig.DEBUG) {
            applovin_banner = getString(R.string.app_lovin_Banner);
        } else {
            applovin_banner = getString(R.string.app_lovin_Banner);
        }
        MaxAdView adView1 = new MaxAdView(applovin_banner, this);
        adView1.setListener(this);

        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = getResources().getDimensionPixelSize(R.dimen.banner_height);

        adView1.setLayoutParams(new FrameLayout.LayoutParams(width, heightPx));

        // Set background or background color for banners to be fully functional

        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.addView(adView1);

        // Load the ad
        adView1.loadAd();
    }

    // MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd) {
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error) {
    }

    @Override
    public void onAdClicked(final MaxAd maxAd) {
    }

    @Override
    public void onAdExpanded(final MaxAd maxAd) {
    }

    @Override
    public void onAdCollapsed(final MaxAd maxAd) {
    }

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

        return new MaxNativeAdView( binder, characterscreen.this );
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