package org.collapsed.ssuparty_android.ui.partylist;

import org.collapsed.ssuparty_android.adapter.PartyListAdapter;
import org.collapsed.ssuparty_android.model.NewPartyInfo;

import java.util.ArrayList;

public interface PartyListContract {
    interface View {
        void initView(android.view.View rootView);

        void showViewInList(int index);

        void addPartyItemToList(NewPartyInfo object);
    }

    interface OnChangeDataListListner {
        void setNewDataToAdapter(ArrayList<NewPartyInfo> dataSet, PartyListAdapter adapter,
                                 NewPartyInfo object);
    }
}
