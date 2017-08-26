package org.collapsed.ssuparty_android;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;


public class GlobalApplication extends Application {
    private static RequestQueue mQueue;
    private static AppConfig mAppConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        initNetworkClient();
        mAppConfig = new AppConfig();
    }

    public static AppConfig getConfig() {
        return mAppConfig;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    private void initNetworkClient() {
        Context BaseContext = getApplicationContext();
        mQueue = Volley.newRequestQueue(BaseContext);

    }

    public static RequestQueue getRequestQueue() {
        return mQueue;
    }
}
