package org.collapsed.ssuparty_android.event.profile;



public class ImageEvent {
    //StorageReference mReference;
    String mUrl;

    public ImageEvent(String url){
        this.mUrl = url;
    }

    public String getReference(){
        return mUrl;
    }

}
