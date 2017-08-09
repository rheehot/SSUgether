package org.collapsed.ssuparty_android.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import org.collapsed.ssuparty_android.GlobalApplication;
import org.collapsed.ssuparty_android.model.NewPartyInfo;
import org.collapsed.ssuparty_android.ui.findparty.FindPartyFragment;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.UserActionListener {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private String mTitle, mCategory, mDeadline, mInfo, mMemberNum;
    private List<String> mTags;

    NewPartyInfo dd;

    private MainActivity mView;

    public MainPresenter(@NonNull MainActivity view) {
        this.mView = checkNotNull(view);
        dd = new NewPartyInfo(mTitle, mMemberNum, mCategory, mDeadline, mInfo, mTags);
    }

    public void initMain() {
        GlobalApplication.getConfig().initializeUserProfile();
    }

    //서버 연동시 이용! 추후에 리턴값은 수정
    NewPartyInfo getDataFromCreateActivity(Intent intent){
        mTitle = intent.getStringExtra("title");
        mCategory = intent.getStringExtra("category");
        mDeadline = intent.getStringExtra("deadline");
        mMemberNum = intent.getStringExtra("memberNum");
        mInfo = intent.getStringExtra("info");
        mTags = intent.getStringArrayListExtra("tag");

        return new NewPartyInfo(mTitle, mMemberNum, mCategory, mDeadline, mInfo, mTags);
    }

}
