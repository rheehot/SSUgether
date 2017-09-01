package org.collapsed.ssuparty_android.ui.home;

import android.support.annotation.NonNull;
import android.util.Log;

import org.collapsed.ssuparty_android.model.profile.UserProfileVO;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class HomePresenter implements HomeContract.UserActionListener {
    private static final String TAG = HomePresenter.class.getSimpleName();

    private HomeFragment mView;

    public HomePresenter(@NonNull HomeFragment view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public ArrayList<UserProfileVO> fetchAllProfiles() {
        Log.d(TAG, "fetchAllProfiles");
        mView.showAllProfiles();
        return null;
    }
}
