package org.collapsed.ssuparty_android.ui.splash;

import android.content.Intent;
import android.support.annotation.NonNull;


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
    }

}
