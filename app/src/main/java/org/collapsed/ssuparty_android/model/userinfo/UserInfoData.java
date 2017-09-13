package org.collapsed.ssuparty_android.model.userinfo;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class UserInfoData {
    public String uid;
    public String email;
    public String name;
    public String nickname;
    public String imgUrl;
    public String major;
    public long grade;
    public long studentID;
    public long gender;
    public String intro;
    public List<String> tags;
    public List<UserPartyStatus> myParty;

    public UserInfoData() {
    }

    public UserInfoData(String uid, String email, String name, String nickname, String imgUrl, String major, long grade, long studentID, long gender, String intro, List<String> tags, List<UserPartyStatus> myParty) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.major = major;
        this.grade = grade;
        this.studentID = studentID;
        this.gender = gender;
        this.intro = intro;
        this.tags = tags;
        this.myParty = myParty;
    }
}
