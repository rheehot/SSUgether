package org.collapsed.ssuparty_android.model.profile;


import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class ProfileData {
    private Long id;
    private String nickName;
    private String grade;
    private String major;
    private String email;
    private String image_uri;
    private String simpleUserIntro;
    private List<String> tag;

    public ProfileData() {
    }

    public ProfileData(Long id, String nickname, String email, String major, String image, String intro, List<String> tags, String grade){
        this.id = id;
        this.nickName = nickname;
        this.email = email;
        this.major = major;
        this.grade = grade;
        this.image_uri = image;
        this.simpleUserIntro = intro;
        this.tag = tags;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickName = nickname;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setProfileImageUrl(String profileImageUri) {
        this.image_uri = profileImageUri;
    }

    public void setIntroduction(String introduction) {
        this.simpleUserIntro = simpleUserIntro;
    }

    public void setTags(List<String> tags) {
        this.tag = tag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mEmail) {
        this.email = mEmail;
    }

    public String getGrade() {
        return grade;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickName;
    }

    public String getMajor() {
        return major;
    }

    public String getProfileImageUri() {
        return image_uri;
    }

    public String getIntroduction() {
        return simpleUserIntro;
    }

    public List<String> getTags() {
        return tag;
    }

}
