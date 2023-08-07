package com.example.house.fakecall_2;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;

import java.util.ArrayList;
import java.util.List;

public class Exit_screen extends AppCompatActivity {

    Button yes;
    TextView no, shareapp, rateus;
    NativeAd fbnativeAd;
    AdView fbadView;

    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;
    private NativeAd nativeAd;
    private final String TAG = "NativeAdActivity".getClass().getSimpleName();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_screen);

        no = findViewById(R.id.btn_no);
        yes = findViewById(R.id.btn_yes);
        shareapp = findViewById(R.id.btn_share);
        rateus = findViewById(R.id.btn_rate);

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                }
            }
        });
        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()));
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Exit_screen.this, home.class);
                startActivity(i);
            }

        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                finish();


            }
        });
        //facebook banner ads
        String placementId = "796890155334581_796891748667755";
        //facebook banner
        fbadView = new AdView(this, placementId, AdSize.BANNER_HEIGHT_50);

        //Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(fbadView);

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Toast.makeText(Exit_screen.this, "Error: " + adError.getErrorMessage(),
                                Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
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

        // Request an ad
        fbadView.loadAd(fbadView.buildLoadAdConfig().withAdListener(adListener).build());
        //facebook native ad initilize
        AudienceNetworkAds.initialize(this);
        loadNativeAd();
//        nativeAd = new NativeAd(this, "796890155334581_796891605334436");
//        NativeAdListener nativeAdListener = new NativeAdListener() {
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//
//                View adView = NativeAdView.render(Exit_screen.this, nativeAd);
//                LinearLayout nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container);
//                // Add the Native Ad View to your ad container.
//                // The recommended dimensions for the ad container are:
//                // Width: 280dp - 500dp
//                // Height: 250dp - 500dp
//                // The template, however, will adapt to the supplied dimensions.
//                nativeAdContainer.addView(adView, new LinearLayout.LayoutParams(MATCH_PARENT, 800));
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//
//            }
//
//            @Override
//            public void onMediaDownloaded(Ad ad) {
//
//            }
//        };
//
//        // Initiate a request to load an ad.
//        nativeAd.loadAd(
//                nativeAd.buildLoadAdConfig()
//                        .withAdListener(nativeAdListener)
//                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
//                        .build());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Exit_screen.this,home.class);
        startActivity(i);
    }
     private void loadNativeAd () {
            // Instantiate a NativeAd object.
            // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
            // now, while you are testing and replace it later when you have signed up.
            // While you are using this temporary code you will only get test ads and if you release
            // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
            nativeAd = new NativeAd(this, "796890155334581_796891605334436");

            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Race condition, load() called again before last ad was displayed
                    if (nativeAd == null || nativeAd != ad) {
                        return;
                    }
                    // Inflate Native Ad into Container
                    inflateAd(nativeAd);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };

            // Request an ad
            nativeAd.loadAd(
                    nativeAd.buildLoadAdConfig()
                            .withAdListener(nativeAdListener)
                            .build());
        }

        private void inflateAd (NativeAd nativeAd){

            nativeAd.unregisterView();

            // Add the Ad view into the ad container.
            nativeAdLayout = findViewById(R.id.native_ad_container);
            LayoutInflater inflater = LayoutInflater.from(Exit_screen.this);
            // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
            adView = (LinearLayout) inflater.inflate(R.layout.facebook_native_ads, nativeAdLayout, false);
            nativeAdLayout.addView(adView);

            // Add the AdOptionsView
            LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
            AdOptionsView adOptionsView = new AdOptionsView(Exit_screen.this, nativeAd, nativeAdLayout);
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            // Create native UI using the ad metadata.
            MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            // Set the Text.
            nativeAdTitle.setText(nativeAd.getAdvertiserName());
            nativeAdBody.setText(nativeAd.getAdBodyText());
            nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
            nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
            sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

            // Create a list of clickable views
            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);

            // Register the Title and CTA button to listen for clicks.
            nativeAd.registerViewForInteraction(
                    adView, nativeAdMedia, nativeAdIcon, clickableViews);

    }
}