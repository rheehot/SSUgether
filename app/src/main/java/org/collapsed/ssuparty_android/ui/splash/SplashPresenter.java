package org.collapsed.ssuparty_android.ui.splash;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import org.collapsed.ssuparty_android.ui.account.AccountActivity;
import org.collapsed.ssuparty_android.ui.main.MainActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class SplashPresenter implements SplashContract.UserActionListener {

    private SplashActivity mView;

    public SplashPresenter(@NonNull SplashActivity view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public void startActivityDependOnLoginState() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Intent intent = new Intent(mView, AccountActivity.class);
                mView.startActivity(intent);
                mView.finish();
            }

            @Override
            public void onNotSignedUp() {
                Intent intent = new Intent(mView, AccountActivity.class);
                mView.startActivity(intent);
                mView.finish();
            }

            @Override
            public void onSuccess(UserProfile result) {
                Intent intent = new Intent(mView, MainActivity.class);
                mView.startActivity(intent);
                mView.finish();
            }
        });
    }
}
