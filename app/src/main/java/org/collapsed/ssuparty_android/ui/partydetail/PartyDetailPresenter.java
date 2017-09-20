package org.collapsed.ssuparty_android.ui.partydetail;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.util.concurrent.ExecutionError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import org.collapsed.ssuparty_android.model.party.ApplyMemberStatus;
import org.collapsed.ssuparty_android.model.party.PartyDB;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyDetailPresenter implements PartyDB.OnApplyStatusChangeListener, PartyDB.OnConvertParticipantListener {

    PartyDetailActivity mView;

    public PartyDetailPresenter(@NonNull PartyDetailActivity view) {
        this.mView = checkNotNull(view);
    }

    public void getPreviousPartyImage(String partyId) {
        PartyDB.readPartyImage(partyId, this);
    }

    public void changePartyImage(String partyId, Uri imageUri) {
        PartyDB.writePartyImage(partyId, imageUri);
    }

    public void updatePartyImage(String imageUrl) {
        mView.inflateImageView(imageUrl);
    }

    public void syncApplyBtnWithStatus(PartyData mPartyData) {
        String currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        for (String participantUid : mPartyData.getParticipants()) {
            if (participantUid.equals(currentUid)) {
                mView.changeApplyButtonText("참가중인 상태");
                return;
            }
        }

        try {
            for (ApplyMemberStatus status : mPartyData.getApplyMemberStatus()) {
                if (status.getUid().equals(currentUid)) {
                    setApplyBtnWithStatus(status.getStatus());
                    return;
                }
            }
        } catch (Exception e) {

        }
    }

    private void setApplyBtnWithStatus(long status) {
        int statusInteger = (int) status;
        switch (statusInteger) {
            case 2:
                mView.changeApplyButtonText("신청 대기 중");
                break;
            case 3:
                mView.changeApplyButtonText("신청 거절 됨");
                break;
        }
    }

    public void applyParty(PartyData partyData) {
        // 비 검증 함수
        PartyDB.sendApplyRequest(this, partyData);
    }

    @Override
    public void onApplyChanged(PartyData changedPartyData) {
        // 비 검증 함수
        syncApplyBtnWithStatus(changedPartyData);
    }

    public void createAdapterItems(PartyData data) {
        for (String uid : data.getParticipants()) {
            PartyDB.convertUidToUserInfo(this, uid);
        }
        try {
            if (data.getFounder().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                for (ApplyMemberStatus status : data.getApplyMemberStatus()) {
                    PartyDB.convertUidToUserInfo(this, status.getUid());
                }
            }
        } catch (Exception e) {
            Log.d("PartyDetailPresenter", e.getMessage());
        }
    }

    @Override
    public void onConvertParticipant(UserInfoData info) {
        mView.addItemIntoAdapter(info);
    }

    public void allowJoinParty(PartyData partyData, UserInfoData data) {
        partyData.getParticipants().add(data.getUid());
        ApplyMemberStatus tempStatus = null;
        for (ApplyMemberStatus status : partyData.getApplyMemberStatus()) {
            if (status.getUid().contains(data.getUid())) {
                tempStatus = status;
            }
        }
        partyData.getApplyMemberStatus().remove(tempStatus);
        PartyDB.requestDecideJoinParty(partyData);
    }

    public void denyJoinParty(PartyData partyData, UserInfoData data) {
        ApplyMemberStatus tempStatus = null;
        for (ApplyMemberStatus status : partyData.getApplyMemberStatus()) {
            if (status.getUid().contains(data.getUid())) {
                tempStatus = status;
            }
        }
        partyData.getApplyMemberStatus().remove(tempStatus);
        PartyDB.requestDecideJoinParty(partyData);
    }
}
