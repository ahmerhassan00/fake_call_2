package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.openApp_Ad;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAppOpenAd;
import com.applovin.sdk.AppLovinSdk;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.BuildConfig;
import com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.R;

public class ExampleAppOpenManager
        implements LifecycleObserver, MaxAdListener {
    private final MaxAppOpenAd appOpenAd;
    Context context;

    //Ads ID here
    String ADS_UNIT ;


    public ExampleAppOpenManager(final Context context) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        if (BuildConfig.DEBUG){
            ADS_UNIT = context.getString(R.string.applovin_openApp);
        }
        else {
            ADS_UNIT = context.getString(R.string.applovin_openApp);
        }
        this.context = context;

        appOpenAd = new MaxAppOpenAd(ADS_UNIT, context);
        appOpenAd.setListener(this);
        appOpenAd.loadAd();
    }

    private void showAdIfReady() {
        if (appOpenAd == null || !AppLovinSdk.getInstance(context).isInitialized()) return;

        if (appOpenAd.isReady()) {
            appOpenAd.showAd(ADS_UNIT);
        } else {
            appOpenAd.loadAd();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        showAdIfReady();
    }

    @Override
    public void onAdLoaded(final MaxAd ad) {
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error) {
        Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d("openAppad","Error: "+ error.getMessage());
    }

    @Override
    public void onAdDisplayed(final MaxAd ad) {
    }

    @Override
    public void onAdClicked(final MaxAd ad) {
    }

    @Override
    public void onAdHidden(final MaxAd ad) {
        appOpenAd.loadAd();
        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdDisplayFailed(final MaxAd ad, final MaxError error) {
        appOpenAd.loadAd();
        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
    }
}