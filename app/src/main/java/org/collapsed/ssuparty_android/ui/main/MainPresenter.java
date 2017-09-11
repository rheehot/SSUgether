package org.collapsed.ssuparty_android.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.squareup.otto.Subscribe;

import org.collapsed.ssuparty_android.event.BusProvider;
import org.collapsed.ssuparty_android.event.PartyEvent;
import org.collapsed.ssuparty_android.model.party.PartyDB;
import org.collapsed.ssuparty_android.model.party.PartyData;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.UserActionListener {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private static final String TITLE_KEY = "title";
    private static final String CATEGORY_KEY = "category";
    private static final String DEADLINE_KEY = "deadline";
    private static final String MEMBER_KEY = "memberNum";
    private static final String INFO_KEY = "info";
    private static final String TAG_KEY = "tag";

    private MainActivity mView;
    private PartyDB mModel;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
        this.mModel = new PartyDB(this);
    }

    public void setNewPartyInfo(Intent partyIntent) {
        String title = partyIntent.getStringExtra(TITLE_KEY);
        String category = partyIntent.getStringExtra(CATEGORY_KEY);
        String deadline = partyIntent.getStringExtra(DEADLINE_KEY);
        String memberNum = partyIntent.getStringExtra(MEMBER_KEY);
        String info = partyIntent.getStringExtra(INFO_KEY);
        List<String> tags = partyIntent.getStringArrayListExtra(TAG_KEY);

        PartyData partyData = new PartyData(title, memberNum, category, deadline, info, tags);

        addNewParty(partyData);
    }

    public void addNewParty(PartyData partyData) {
        mModel.writeNewParty(partyData);
    }

    public void updatePartyList (PartyData partyData) {
        mView.getAllPartyFragment().addPartyItemToList(partyData);
    }
}
