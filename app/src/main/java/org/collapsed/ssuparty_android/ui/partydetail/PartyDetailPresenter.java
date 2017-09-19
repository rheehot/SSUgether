package org.collapsed.ssuparty_android.ui.partydetail;

import android.net.Uri;
import android.support.annotation.NonNull;


import org.collapsed.ssuparty_android.model.party.PartyDB;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyDetailPresenter {

    PartyDetailActivity mView;

    public PartyDetailPresenter (@NonNull PartyDetailActivity view) {
        this.mView = checkNotNull(view);
    }

    public void getPreviousPartyImage(String partyId) {
        PartyDB.readPartyImage(partyId, this);
    }

    public void changePartyImage(String partyId, Uri imageUri){
        PartyDB.writePartyImage(partyId, imageUri);
    }

    public void updatePartyImage(String imageUrl) {
        mView.inflateImageView(imageUrl);
    }
}
