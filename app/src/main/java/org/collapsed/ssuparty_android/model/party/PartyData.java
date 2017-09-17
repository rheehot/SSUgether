package org.collapsed.ssuparty_android.model.party;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@IgnoreExtraProperties
public class PartyData {
    private String title;
    private boolean status;
    private String category;
    private long currentMemberNum;
    private String description;
    private String founder;
    private long maxMumberNum;
    private String partyID;
    private String recruitDate;
    private List<String> participants;
    private List<String> tags;

    public PartyData() {
    }

    public PartyData(String title, boolean status, String category, long currentMemberNum, String description, String founder, long maxMumberNum, String recruitDate, List<String> tags) {
        this.title = title;
        this.status = status;
        this.category = category;
        this.currentMemberNum = currentMemberNum;
        this.description = description;
        this.founder = founder;
        this.maxMumberNum = maxMumberNum;
        this.recruitDate = recruitDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getCurrentMemberNum() {
        return currentMemberNum;
    }

    public void setCurrentMemberNum(long currentMemberNum) {
        this.currentMemberNum = currentMemberNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public long getMaxMumberNum() {
        return maxMumberNum;
    }

    public void setMaxMumberNum(long maxMumberNum) {
        this.maxMumberNum = maxMumberNum;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public String getRecruitDate() {
        return recruitDate;
    }

    public void setRecruitDate(String recruitDate) {
        this.recruitDate = recruitDate;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
