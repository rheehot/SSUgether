package org.collapsed.ssuparty_android.model.unionsearch;

import org.collapsed.ssuparty_android.model.contest.ContestData;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.profile.ProfileData;

import java.util.ArrayList;

public class UnionSearchData {
    private ArrayList<ProfileData> profiles;
    private ArrayList<PartyData> partys;
    private ArrayList<ContestData> contests;

    public UnionSearchData() {
    }

    public UnionSearchData(ArrayList<ProfileData> profiles, ArrayList<PartyData> partys, ArrayList<ContestData> contests) {
        this.profiles = profiles;
        this.partys = partys;
        this.contests = contests;
    }

    public ArrayList<ProfileData> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<ProfileData> profiles) {
        this.profiles = profiles;
    }

    public ArrayList<PartyData> getPartys() {
        return partys;
    }

    public void setPartys(ArrayList<PartyData> partys) {
        this.partys = partys;
    }

    public ArrayList<ContestData> getContests() {
        return contests;
    }

    public void setContests(ArrayList<ContestData> contests) {
        this.contests = contests;
    }
}
