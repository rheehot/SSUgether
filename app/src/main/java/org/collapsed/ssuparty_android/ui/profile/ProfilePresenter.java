package org.collapsed.ssuparty_android.ui.profile;

import android.net.Uri;
import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.model.profile.ProfileDB;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProfilePresenter {

    private ProfileFragment mView;
    private ProfileDB mModel;

    public ProfilePresenter(@NonNull ProfileFragment view) {
        this.mView = checkNotNull(view);
        this.mModel = new ProfileDB(this);
    }

    public void getPreviousProfileData() {
        mModel.readProfileImage();
        mModel.readProfileIntro();
        mModel.readProfieTagList();
    }

    public void changeProfileImageUrl(Uri imageUri) {
        mModel.writeProfileImage(imageUri);
    }

    public void changeProfileIntro(String introText) {
        mModel.writeIntroduction(introText);
    }

    public void changeProfileTagList(String[] tagList) {
        mModel.writeTagList(tagList);
    }

    public void loadIntroData(String introText) {
        mView.inflateIntroView(introText);
    }

    public void loadTagListData(List<String> tagList) {
        String[] tagArray = tagList.toArray(new String[tagList.size()]);
        mView.inflateTagView(tagArray);
    }

    public void loadImageData(String imageUrl) {
        mView.inflateImageView(imageUrl);
    }
}
