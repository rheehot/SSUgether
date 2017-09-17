package org.collapsed.ssuparty_android.ui.partylist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.ui.partydetail.PartyDetailActivity;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyListPresenter {

    private static final String TAG = PartyListPresenter.class.getSimpleName();

    private static final String TITLE_KEY = "title";
    private static final String CATEGORY_KEY = "category";
    private static final String DEADLINE_KEY = "deadline";
    private static final String MEMBER_KEY = "memberNum";
    private static final String INFO_KEY = "info";
    private static final String TAG_KEY = "tag";
    private static final String PARTY_KEY = "key";

    private PartyListFragment mView;

    public PartyListPresenter(@NonNull PartyListFragment view) {
        this.mView = checkNotNull(view);
    }

    public void createPartyDetail(Context context, PartyData partyData) {
        Intent intent = new Intent(context, PartyDetailActivity.class);
        intent.putExtra(TITLE_KEY, partyData.getTitle());
        intent.putExtra(CATEGORY_KEY, partyData.getCategory());
        intent.putExtra(DEADLINE_KEY, partyData.getRecruitDate());
        intent.putExtra(MEMBER_KEY, partyData.getMaxMumberNum());
        intent.putExtra(INFO_KEY, partyData.getDescription());
        intent.putExtra(PARTY_KEY, partyData.getPartyID());
        intent.putStringArrayListExtra(TAG_KEY, (ArrayList<String>) partyData.getTags());

        mView.showPartyDetail(intent);
    }
}
