package org.collapsed.ssuparty_android.model.profile;


import java.util.List;

public class ProfileData {
    private String mId;
    private String mNickname;
    private String mGrade;
    private String mMajor;
    private String mProfileImageUri;
    private String mIntroduction;
    private List<String> mTags;

    public ProfileData(String id, String nickname, String major, String image, String intro, List<String> tags){
        this.mId = id;
        this.mNickname = nickname;
        this.mMajor = major;
        this.mProfileImageUri = image;
        this.mIntroduction = intro;
        this.mTags = tags;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public void setNickname(String nickname) {
        this.mNickname = nickname;
    }

    public void setGrade(String grade) {
        this.mGrade = grade;
    }

    public void setMajor(String major) {
        this.mMajor = major;
    }

    public void setProfileImageUrl(String profileImageUri) {
        this.mProfileImageUri = profileImageUri;
    }

    public void setIntroduction(String introduction) {
        this.mIntroduction = mIntroduction;
    }

    public void setTags(List<String> tags) {
        this.mTags = mTags;
    }

    public String getId() {
        return mId;
    }

    public String getNickname() {
        return mNickname;
    }

    public String getGrade() {
        return mGrade;
    }

    public String getMajor() {
        return mMajor;
    }

    public String getProfileImageUri() {
        return mProfileImageUri;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public List<String> getTags() {
        return mTags;
    }

}
