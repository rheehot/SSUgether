package org.collapsed.ssuparty_android.ui.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.UserActionListener {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
    }

    public void getUserInfoFromKaKao() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d(TAG, errorResult.getErrorMessage());
            }

            @Override
            public void onNotSignedUp() {
                Log.d(TAG, "onNotSignedUp");
            }

            @Override
            public void onSuccess(UserProfile result) {
                Log.d(TAG, "Email : " + result.getEmail());
                Log.d(TAG, "Nickname : " + result.getNickname());
                Log.d(TAG, "Profile Image : " + result.getThumbnailImagePath());
            }
        });
    }
}
