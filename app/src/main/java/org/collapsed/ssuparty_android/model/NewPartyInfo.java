package org.collapsed.ssuparty_android.model;

import java.io.Serializable;
import java.util.List;

public class NewPartyInfo implements Serializable {
    private int mImage;
    private String mTitle;
    private String mMemberNum;
    private String mCategory;
    private String mDeadline;
    private String mInformation;
    private List<String> mTags;

    public NewPartyInfo(){}

    public NewPartyInfo(String title, String memberNum, String category,
    String deadline, String information, List<String> tags){
        mTitle = title;
        mMemberNum = memberNum;
        mCategory = category;
        mDeadline = deadline;
        mInformation = information;
        mTags = tags;
    }

    public int getImage(){
        return mImage;
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
