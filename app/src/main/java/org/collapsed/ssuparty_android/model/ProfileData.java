package org.collapsed.ssuparty_android.model;

public class ProfileData {
    private String mNickname;
    private String mGrade;
    private String mMajor;
    private String mProfileImageUrl;

    public ProfileData(String imageUrl, String nickname, String major, String grade){
        this.mProfileImageUrl = imageUrl;
        this.mNickname = nickname;
        this.mGrade = grade;
        this.mMajor = major;
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

    public String getProfileImageId() {
        return mProfileImageUrl;
    }
}
