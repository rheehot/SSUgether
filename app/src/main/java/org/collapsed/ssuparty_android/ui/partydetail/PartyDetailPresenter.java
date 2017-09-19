package org.collapsed.ssuparty_android.ui.partydetail;

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

    public void changePartyImage(String partyId, String imageUrl){
        PartyDB.writePartyImage(partyId, imageUrl.toString());
    }

    public void updatePartyImage(String imageUrl) {
        mView.inflateImageView(imageUrl);
    }
}
