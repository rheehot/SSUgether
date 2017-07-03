package org.collapsed.ssuparty_android;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {
    private static RequestQueue mQueue;
    private Config mConfig;
    private static Context mGlobalAppContext;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mGlobalAppContext = getApplicationContext();
        KakaoSDK.init(new KakaoSDKAdapter());
        initNetworkClient();
        initFontSetup();
        mConfig = new Config();
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

    private static class KakaoSDKAdapter extends KakaoAdapter {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         *
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[]{AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return mGlobalAppContext;
                }
            };
        }
    }
}
