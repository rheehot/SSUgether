package org.collapsed.ssuparty_android.model.userprofile;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserProfileData {
    public String uid;
    public String email;
    public String name;
    public String imgUrl;
    public String nickname;
    public String major;
    public long grade;
    public long schoolid;
    public long gender;

    public UserProfileData() {
    }

    public UserProfileData(String uid, String email, String imgUrl, String name, String nickname, String major, long grade, long schoolid, long gender) {
        this.uid = uid;
        this.email = email;
        this.imgUrl = imgUrl;
        this.name = name;
        this.nickname = nickname;
        this.major = major;
        this.grade = grade;
        this.schoolid = schoolid;
        this.gender = gender;
    }
}
