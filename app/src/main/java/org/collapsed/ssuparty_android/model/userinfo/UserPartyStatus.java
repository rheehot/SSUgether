package org.collapsed.ssuparty_android.model.userinfo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
class UserPartyStatus {
    public int status;
    public int partyID;

    public UserPartyStatus() {
    }

    public UserPartyStatus(int status, int partyID) {
        this.status = status;
        this.partyID = partyID;
    }
}


