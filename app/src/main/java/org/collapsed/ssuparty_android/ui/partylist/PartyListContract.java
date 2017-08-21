package org.collapsed.ssuparty_android.ui.partylist;

import org.collapsed.ssuparty_android.adapter.PartyListAdapter;
import org.collapsed.ssuparty_android.model.PartyData;

import java.util.ArrayList;

public interface PartyListContract {
    interface View {
        void initView(android.view.View rootView);

        void inflateView(int index);

        void addPartyItemToList(PartyData object);
    }

    interface OnChangeDataListListner {
        void setNewDataToAdapter(ArrayList<PartyData> dataSet, PartyListAdapter adapter,
                                 PartyData object);
    }
}
