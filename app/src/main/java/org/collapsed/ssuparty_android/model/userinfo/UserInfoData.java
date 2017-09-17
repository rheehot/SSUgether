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
    public String grade;
    public long studentID;
    public long gender;
    public String intro;
    public List<String> tags;
    public List<UserPartyStatus> myParty;

    public UserInfoData() {
    }

    public UserInfoData(String uid, String email, String name, String nickname, String imgUrl, String major, String grade, long studentID, long gender, String intro, List<String> tags, List<UserPartyStatus> myParty) {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<UserPartyStatus> getMyParty() {
        return myParty;
    }

    public void setMyParty(List<UserPartyStatus> myParty) {
        this.myParty = myParty;
    }
}
