package org.collapsed.ssuparty_android.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.GlobalApplication;
import org.collapsed.ssuparty_android.model.FirebaseDB;
import org.collapsed.ssuparty_android.model.PartyData;

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

    private String mTitle, mCategory, mDeadline, mInfo, mMemberNum;
    private List<String> mTags;

    private MainActivity mView;
    private FirebaseDB mModel;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
        this.mModel = new FirebaseDB(this);
    }

    public void initMain() {
        GlobalApplication.getConfig().initializeUserProfile();
    }

    //서버 연동시 이용! 추후에 리턴값은 수정
    public void getCreatedPartyInfo(Intent intent){
        mTitle = intent.getStringExtra(TITLE_KEY);
        mCategory = intent.getStringExtra(CATEGORY_KEY);
        mDeadline = intent.getStringExtra(DEADLINE_KEY);
        mMemberNum = intent.getStringExtra(MEMBER_KEY);
        mInfo = intent.getStringExtra(INFO_KEY);
        mTags = intent.getStringArrayListExtra(TAG_KEY);

        PartyData partyData = new PartyData(mTitle, mMemberNum, mCategory, mDeadline, mInfo, mTags);

        setDataInDB(DB_ALL_PARTY_KEY,partyData);
        setDataInDB(DB_MY_PARTY_KEY,partyData);
    }

    public void setNewPartyList(PartyData partyData) {
        mView.getCommonListFragmentObeject().addPartyItemToList(partyData);
    }

    public void setDataInDB(String key, Object data) {
        mModel.pushDataToFirebaseDB(key, data);
    }
}
