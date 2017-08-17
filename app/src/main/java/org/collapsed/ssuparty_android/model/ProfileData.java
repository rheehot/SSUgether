package org.collapsed.ssuparty_android.model;

public class ProfileData {
    private String mNickname;
    private String mEducation;
    private String mCarrer;
    private String mProfileImageUrl;

    public ProfileData(){}

    public ProfileData(String imageId, String nickname, String education, String carrer){
        this.mProfileImageUrl = imageId;
        this.mNickname = nickname;
        this.mEducation = education;
        this.mCarrer = carrer;
    }

    public String getNickname() {
        return mNickname;
    }

    public String getEducation() {
        return mEducation;
    }

    public String getCarrer() {
        return mCarrer;
    }

    public String getProfileImageId() {
        return mProfileImageUrl;
    }
}
