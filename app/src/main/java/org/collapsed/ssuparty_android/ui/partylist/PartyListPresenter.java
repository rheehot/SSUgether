package org.collapsed.ssuparty_android.ui.partylist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.collapsed.ssuparty_android.model.party.PartyDB;
import org.collapsed.ssuparty_android.model.party.PartyData;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyListPresenter {

    private static final String TAG = PartyListPresenter.class.getSimpleName();

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private PartyListFragment mView;

    public PartyListPresenter(@NonNull PartyListFragment view) {
        this.mView = checkNotNull(view);
    }
}
