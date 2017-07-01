package org.collapsed.ssuparty_android.ui.myparty;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

class MypartyPresenter implements MyPartyContract.UserActionListener {
    private static final String TAG = MypartyPresenter.class.getSimpleName();

    private MyPartyFragment mView;

    public MypartyPresenter(@NonNull MyPartyFragment view) {
        this.mView = checkNotNull(view);
    }
}
