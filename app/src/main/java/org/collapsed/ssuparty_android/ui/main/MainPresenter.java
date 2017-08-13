package org.collapsed.ssuparty_android.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.GlobalApplication;
import org.collapsed.ssuparty_android.model.NewPartyInfo;

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

    private String mTitle, mCategory, mDeadline, mInfo, mMemberNum;
    private List<String> mTags;

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
    }

    public void initMain() {
        GlobalApplication.getConfig().initializeUserProfile();
    }

    //서버 연동시 이용! 추후에 리턴값은 수정
    public NewPartyInfo getDataFromCreateActivity(Intent intent){
        mTitle = intent.getStringExtra(TITLE_KEY);
        mCategory = intent.getStringExtra(CATEGORY_KEY);
        mDeadline = intent.getStringExtra(DEADLINE_KEY);
        mMemberNum = intent.getStringExtra(MEMBER_KEY);
        mInfo = intent.getStringExtra(INFO_KEY);
        mTags = intent.getStringArrayListExtra(TAG_KEY);

        NewPartyInfo partyData = new NewPartyInfo(mTitle, mMemberNum, mCategory, mDeadline, mInfo, mTags);

        setNewPartyList(partyData);
        return partyData;
    }

    public void setNewPartyList(NewPartyInfo object){
        mView.getCommonListFragmentObeject().addPartyItemToList(object);
    }

}
