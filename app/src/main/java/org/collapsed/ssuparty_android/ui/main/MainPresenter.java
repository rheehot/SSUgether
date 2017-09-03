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

    private static final String DB_ALL_PARTY_KEY = "all_party";
    private static final String DB_MY_PARTY_KEY = "my_party";

    private static final int INDEX_HOME = 0;
    private static final int INDEX_MY_PARTY = 1;
    private static final int INDEX_ALL_PARTY = 2;

    private String mTitle, mCategory, mDeadline, mInfo, mMemberNum;
    private List<String> mTags;

    private MainActivity mView;
    private PartyDB mModel;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
        this.mModel = new PartyDB(this);
        BusProvider.getInstance().register(this);
    }

    public void initMain() {
    }

    //서버 연동시 이용! 추후에 리턴값은 수정
    public void getCreatedPartyInfo(Intent intent) {
        mTitle = intent.getStringExtra(TITLE_KEY);
        mCategory = intent.getStringExtra(CATEGORY_KEY);
        mDeadline = intent.getStringExtra(DEADLINE_KEY);
        mMemberNum = intent.getStringExtra(MEMBER_KEY);
        mInfo = intent.getStringExtra(INFO_KEY);
        mTags = intent.getStringArrayListExtra(TAG_KEY);

        PartyData partyData = new PartyData(mTitle, mMemberNum, mCategory, mDeadline, mInfo, mTags);

        setDataInFirebase(DB_ALL_PARTY_KEY, partyData);
        setDataInFirebase(DB_MY_PARTY_KEY, partyData);
    }

    @Subscribe
    public void onPartyAdded(PartyEvent addEvent) {
        switch (addEvent.getKey()) {
            case INDEX_MY_PARTY:
                mView.getMyPartyFragment().addPartyItemToList(addEvent.getPartyData());
                break;

            case INDEX_ALL_PARTY:
                mView.getAllPartyFragment().addPartyItemToList(addEvent.getPartyData());
                break;
        }
    }

    public void setDataInFirebase(String key, Object data) {
        mModel.writeNewParty(key, data);
    }

}
