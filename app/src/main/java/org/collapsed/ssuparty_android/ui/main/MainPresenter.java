package org.collapsed.ssuparty_android.ui.main;

import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.GlobalApplication;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.UserActionListener {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
    }

    public void initMain() {
        GlobalApplication.getConfig().initializeUserProfile();
    }
}
