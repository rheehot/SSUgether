package org.collapsed.ssuparty_android.model.profile;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ProfileData {
    private Long id;
    private String nickName;
    private String grade;
    private String major;
    private String email;
    private String imageUrl;
    private String simpleUserIntro;

    public ProfileData() {
    }

    public ProfileData(Long id, String nickName, String email, String major, String imageUrl, String simpleUserIntro, String grade) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.major = major;
        this.grade = grade;
        this.imageUrl = imageUrl;
        this.simpleUserIntro = simpleUserIntro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSimpleUserIntro() {
        return simpleUserIntro;
    }

    public void setSimpleUserIntro(String simpleUserIntro) {
        this.simpleUserIntro = simpleUserIntro;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMajor() {
        return major;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
