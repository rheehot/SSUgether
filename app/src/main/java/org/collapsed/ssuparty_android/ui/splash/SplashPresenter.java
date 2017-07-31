package org.collapsed.ssuparty_android.ui.splash;

import android.content.Intent;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class SplashPresenter implements SplashContract.UserActionListener {

    private SplashActivity mView;

    public SplashPresenter(@NonNull SplashActivity view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public Intent getIntentForNextActivity() {
        //Todo : 현재 앱이 로그인되어 있는지 아닌지 판단하고 상황에 맞는 다음 Activity를 실행.
        return null;
    }
}
