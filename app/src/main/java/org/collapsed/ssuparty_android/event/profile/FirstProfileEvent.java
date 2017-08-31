package org.collapsed.ssuparty_android.event.profile;

public class FirstProfileEvent {
    String mUrl;

    public FirstProfileEvent(String url){
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }
}
