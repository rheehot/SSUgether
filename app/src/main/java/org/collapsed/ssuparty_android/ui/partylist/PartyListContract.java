package org.collapsed.ssuparty_android.ui.partylist;

import org.collapsed.ssuparty_android.model.party.PartyData;


public interface PartyListContract {
    interface View {
        void initView(android.view.View rootView);

        void inflateView(int index);

        void addPartyItemToList(PartyData object);
    }
}
