package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.openApp_Ad;

import android.app.Application;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class MyApplication extends Application
{
    private static ExampleAppOpenManager appOpenManager;
    @Override
    public void onCreate()
    {
        super.onCreate();

        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener()
        {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                appOpenManager = new ExampleAppOpenManager( MyApplication.this );

            }
        } );
    }
}