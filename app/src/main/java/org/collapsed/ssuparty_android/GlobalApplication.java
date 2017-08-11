package org.collapsed.ssuparty_android;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.kakao.auth.KakaoSDK;

import org.collapsed.ssuparty_android.utils.KakaoSDKAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class GlobalApplication extends Application {
    private static RequestQueue mQueue;
    private static AppConfig mAppConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSDK.init(new KakaoSDKAdapter(getApplicationContext()));
        initNetworkClient();
        initFontSetup();
        mAppConfig = new AppConfig();
    }

    public static AppConfig getConfig() {
        return mAppConfig;
    }

    private void initFontSetup() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NanumSquareOTFRegular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
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