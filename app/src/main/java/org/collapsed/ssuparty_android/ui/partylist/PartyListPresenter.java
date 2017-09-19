package org.collapsed.ssuparty_android.ui.partylist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;

import org.collapsed.ssuparty_android.model.party.PartyDB;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.ui.partydetail.PartyDetailActivity;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyListPresenter implements PartyDB.OnPartyDataFetchedListener {

    private static final String TAG = PartyListPresenter.class.getSimpleName();

    private PartyListFragment mView;

    public PartyListPresenter(@NonNull PartyListFragment view) {
        this.mView = checkNotNull(view);
    }

    public void createPartyDetail(Context context, PartyData partyData) {
        Intent intent = new Intent(context, PartyDetailActivity.class);

        intent.putExtra("PartyData", partyData);
        mView.showPartyDetail(intent);
    }

    // 메인에서 호출
    public void addNewParty(Intent intent) {
        PartyData newPartyData = (PartyData) intent.getSerializableExtra("PartyData");
        PartyDB.writeNewParty(newPartyData);
    }

    //마이파티 탭 눌렀을때
    public void fetchMyPartyList() {
        mView.clearList();
        PartyDB.executeFetch(this, FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    //전체파티 탭 눌렀을때
    public void fetchAllPartyList() {
        mView.clearList();
        PartyDB.fetchAllParty(this);
    }

    @Override
    public void onFetched(PartyData data) {
        mView.addPartyItemToList(data);
    }


}
