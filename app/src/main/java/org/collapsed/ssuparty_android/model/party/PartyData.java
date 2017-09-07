package org.collapsed.ssuparty_android.model.party;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class PartyData {
    private String mTitle;
    private String mMemberNum;
    private String mCategory;
    private String mDeadline;
    private String mInformation;
    private ArrayList<String> mTags;

    public PartyData(){}

    public PartyData(String title, String memberNum, String category,
                     String deadline, String information, List<String> tags){
        this.mTitle = title;
        this.mMemberNum = memberNum;
        this.mCategory = category;
        this.mDeadline = deadline;
        this.mInformation = information;
        this.mTags = (ArrayList<String>) tags;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setMemberNum(String mMemberNum) {
        this.mMemberNum = mMemberNum;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public void setDeadline(String mDeadline) {
        this.mDeadline = mDeadline;
    }

    public void setInformation(String mInformation) {
        this.mInformation = mInformation;
    }

    public void setTags(ArrayList<String> mTags) {
        this.mTags = mTags;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMemberNum() {
        return mMemberNum;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDeadline() {
        return mDeadline;
    }

    public String getInformation() {
        return mInformation;
    }

    public List<String> getTags() {
        return mTags;
    }
}
