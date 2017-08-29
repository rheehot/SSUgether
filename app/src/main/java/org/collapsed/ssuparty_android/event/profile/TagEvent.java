package org.collapsed.ssuparty_android.event.profile;

import java.util.List;

public class TagEvent {
    List<String> mTags;

    public TagEvent(List<String> tags){
        mTags = tags;
    }

    public List<String> getTags() {
        return mTags;
    }
}
