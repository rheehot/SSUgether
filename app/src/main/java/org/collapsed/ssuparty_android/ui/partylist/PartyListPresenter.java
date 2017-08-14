package org.collapsed.ssuparty_android.ui.partylist;

import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.adapter.PartyListAdapter;
import org.collapsed.ssuparty_android.model.NewPartyInfo;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyListPresenter implements PartyListContract.OnChangeDataListListner {

    private static final String TAG = PartyListPresenter.class.getSimpleName();

    private PartyListFragment mView;

    public PartyListPresenter(@NonNull PartyListFragment view) {
        this.mView = checkNotNull(view);
    }

    public void setNewDataToAdapter(ArrayList<NewPartyInfo> dataSet, PartyListAdapter adapter,
                                   NewPartyInfo object){
        dataSet.add(object);
        adapter.addItem(dataSet);
    }

}
