package org.collapsed.ssuparty_android.model;

public class Party {
    private int mImage;
    private String mTitle;
    private String mMemberNum;

    public Party(int image, String title, String memberNum){
        mImage = image;
        mTitle = title;
        mMemberNum = memberNum;
    }

    public int getImage(){
        return mImage;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getMemberNum(){
        return mMemberNum;
    }
}
