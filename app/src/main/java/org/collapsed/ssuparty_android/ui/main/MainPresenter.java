package org.collapsed.ssuparty_android.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import org.collapsed.ssuparty_android.model.party.PartyDB;
import org.collapsed.ssuparty_android.model.party.PartyData;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.UserActionListener, PartyDB.OnPartyDataFetchedListener {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public void onFetched(PartyData data) {

    }
}
