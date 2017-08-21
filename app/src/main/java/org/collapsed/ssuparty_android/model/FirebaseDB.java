package org.collapsed.ssuparty_android.model;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.collapsed.ssuparty_android.ui.main.MainPresenter;

public class FirebaseDB {

    private static final String DB_ALL_PARTY_KEY = "all_party";
    private static final String DB_MY_PARTY_KEY = "my_party";

    private DatabaseReference mRootRef, mAllPartyRef, mMyPartyRef;
    private MainPresenter mPresenter;

    public FirebaseDB(MainPresenter presenter) {
        this.mPresenter = presenter;

        initModel();
    }

    public void initModel() {
        this.mRootRef = FirebaseDatabase.getInstance().getReference();
        this.mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        this.mMyPartyRef = mRootRef.child(DB_MY_PARTY_KEY);

        mAllPartyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PartyData partydata = dataSnapshot.getValue(PartyData.class);
                mPresenter.setNewPartyList(partydata);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

    public void pushDataToFirebaseDB(String key, Object data) {
        switch (key) {
            case DB_ALL_PARTY_KEY:
                mAllPartyRef.push().setValue(data);
                break;

            case DB_MY_PARTY_KEY:
                //mMyPartyRef.push().setValue(data);
                break;

            default:
                break;
        }
    }
}
