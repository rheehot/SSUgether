package org.collapsed.ssuparty_android.model.party;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class ApplyMemberStatus implements Serializable {
    private String uid;
    private long status;

    public ApplyMemberStatus() {
    }

    public ApplyMemberStatus(String uid, int status) {
        this.uid = uid;
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
