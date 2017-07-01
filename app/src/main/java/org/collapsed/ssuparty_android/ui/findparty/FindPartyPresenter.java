package org.collapsed.ssuparty_android.ui.findparty;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

class FindPartyPresenter implements FindPartyContract.UserActionListener {
    private static final String TAG = FindPartyPresenter.class.getSimpleName();

    private FindPartyFragment mView;

    public FindPartyPresenter(@NonNull FindPartyFragment view) {
        this.mView = checkNotNull(view);
    }
}
