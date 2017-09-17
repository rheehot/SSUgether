package org.collapsed.ssuparty_android.model.unionsearch;

import org.collapsed.ssuparty_android.model.contest.ContestData;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;

import java.util.ArrayList;

public class UnionSearchData {
    private ArrayList<UserInfoData> userDatas;
    private ArrayList<PartyData> partys;
    private ArrayList<ContestData> contests;

    public UnionSearchData() {
    }

    public UnionSearchData(ArrayList<UserInfoData> userDatas, ArrayList<PartyData> partys, ArrayList<ContestData> contests) {
        this.userDatas = userDatas;
        this.partys = partys;
        this.contests = contests;
    }

    public ArrayList<UserInfoData> getProfiles() {
        return userDatas;
    }

    public void setProfiles(ArrayList<UserInfoData> profiles) {
        this.userDatas = profiles;
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
