package org.collapsed.ssuparty_android.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import org.collapsed.ssuparty_android.model.party.PartyDB;
import org.collapsed.ssuparty_android.model.party.PartyData;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.UserActionListener, PartyDB.OnPartyDataFetchedListener {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private static final String TITLE_KEY = "title";
    private static final String CATEGORY_KEY = "category";
    private static final String DEADLINE_KEY = "deadline";
    private static final String MEMBER_KEY = "memberNum";
    private static final String INFO_KEY = "info";
    private static final String TAG_KEY = "tag";

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
    }

    public void setNewPartyInfo(Intent partyIntent) {
        String title = partyIntent.getStringExtra(TITLE_KEY);
        String category = partyIntent.getStringExtra(CATEGORY_KEY);
        String deadline = partyIntent.getStringExtra(DEADLINE_KEY);
        long memberNum = Long.parseLong(partyIntent.getStringExtra(MEMBER_KEY));
        String info = partyIntent.getStringExtra(INFO_KEY);
        List<String> tags = partyIntent.getStringArrayListExtra(TAG_KEY);
        String founder = FirebaseAuth.getInstance().getCurrentUser().getUid();

        PartyData partyData = new PartyData(title, true, category, 1, info, founder, memberNum, deadline, tags);

        addNewParty(partyData);
    }

    public void addNewParty(PartyData partyData) {
        PartyDB.writeNewParty(partyData);
    }

    @Override
    public void onFetched(PartyData data) {

    }
}
