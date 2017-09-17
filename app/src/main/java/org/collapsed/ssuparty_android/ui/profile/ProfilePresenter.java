package org.collapsed.ssuparty_android.ui.profile;

import android.net.Uri;
import android.support.annotation.NonNull;


import com.google.firebase.auth.FirebaseAuth;

import org.collapsed.ssuparty_android.model.userinfo.UserInfoDB;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProfilePresenter implements UserInfoDB.OnUserInfoFetchListener {
    private ProfileFragment mView;
    private UserInfoDB mModel;

    public ProfilePresenter(@NonNull ProfileFragment view) {
        this.mView = checkNotNull(view);
        this.mModel = new UserInfoDB(this, FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public void getPreviousProfileDatas() {
        mModel.fetchUserProfileData();
    }

    public void changeProfileImage(Uri imageUri) {
        mModel.writeProfileImage(imageUri);
    }

    public void changeProfileIntro(String introText) {
        mModel.writeIntroduction(introText);
    }

    public void changeProfileTagList(String[] tagList) {
        mModel.writeTagList(tagList);
    }

    public void updateIntro(String introText) {
        mView.inflateIntroText(introText);
    }

    public void updateTagList(List<String> tagList) {
        mView.inflateTagView(tagList);
    }

    public void updateImage(String imageUrl) {
        mView.inflateImageView(imageUrl);
    }

    public void updateName(String nameText) {
        mView.inflateNameText(nameText);
    }

    public void updateGrade(String gradeText) {
        mView.inflateGradeText(gradeText);
    }

    public void updateMajor(String majorText) {
        mView.inflateMajorText(majorText);
    }


    @Override
    public void onFetched(UserInfoData data) {
        updateName(data.getName());
        updateGrade(data.getGrade());
        updateMajor(data.getMajor());
        updateTagList(data.getTags());
        updateIntro(data.getIntro());
        updateImage(data.getImgUrl());
    }
}
