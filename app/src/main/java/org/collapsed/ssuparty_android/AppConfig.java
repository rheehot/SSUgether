package org.collapsed.ssuparty_android;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

public class AppConfig {
    private static final String TAG = AppConfig.class.getSimpleName();

    public static final String BASE_BANNER_ADDRESS = "http://52.79.95.19:8000/advertise/banner";

    public AppConfig() {

    }

    public void initializeUserProfile() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(UserProfile result) {

            }
        });
    }
}
