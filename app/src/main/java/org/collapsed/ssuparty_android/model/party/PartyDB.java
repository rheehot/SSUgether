package org.collapsed.ssuparty_android.model.party;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.collapsed.ssuparty_android.ui.main.MainPresenter;

public class PartyDB {

    private static final String DB_ALL_PARTY_KEY = "allParty";

    private MainPresenter mPresenter;
    private DatabaseReference mRootRef, mAllPartyRef;

    public PartyDB(MainPresenter presenter) {
        mPresenter = presenter;
        initModel();
    }

    public void initModel() {
        this.mRootRef = FirebaseDatabase.getInstance().getReference();
        this.mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);

        this.mAllPartyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PartyData partyData = dataSnapshot.getValue(PartyData.class);
                mPresenter.updatePartyList(partyData);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void writeNewParty(PartyData partyData) {
        String partyKey = mAllPartyRef.push().getKey();
        partyData.setPartyID(partyKey);
        mAllPartyRef.child(partyKey).setValue(partyData);
    }
}
