package org.collapsed.ssuparty_android.event;

import org.collapsed.ssuparty_android.model.PartyData;

public class PartyEvent {
    private PartyData mPartyData;
    private int mKey;

    public PartyEvent(PartyData partyData, int key){
        mPartyData = partyData;
        mKey = key;
    }

    public PartyData getPartyData(){
        return mPartyData;
    }

    public int getKey(){
        return mKey;
    }
}
