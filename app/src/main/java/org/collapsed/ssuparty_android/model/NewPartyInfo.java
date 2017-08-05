package org.collapsed.ssuparty_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewPartyInfo {
    private String mTitle;
    private String mMemberNum;
    private String mCategory;
    private String mDeadline;
    private String mInformation;
    private ArrayList<String> mTags;

    public NewPartyInfo(){}

    public NewPartyInfo(String title, String memberNum, String category,
    String deadline, String information, List<String> tags){
        this.mTitle = title;
        this.mMemberNum = memberNum;
        this.mCategory = category;
        this.mDeadline = deadline;
        this.mInformation = information;
        this.mTags = (ArrayList<String>) tags;
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
