package org.collapsed.ssuparty_android.model;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.otto.Bus;

import org.collapsed.ssuparty_android.AppConfig;
import org.collapsed.ssuparty_android.event.BusProvider;
import org.collapsed.ssuparty_android.event.PartyEvent;
import org.collapsed.ssuparty_android.ui.main.MainPresenter;

public class PartyDB {

    private static final String DB_ALL_PARTY_KEY = "all_party";
    private static final String DB_MY_PARTY_KEY = "my_party";
    private static final int INDEX_HOME = 0;
    private static final int INDEX_MY_PARTY = 1;
    private static final int INDEX_ALL_PARTY = 2;

    private DatabaseReference mRootRef, mAllPartyRef, mMyPartyRef;

    public PartyDB(MainPresenter presenter) {

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
                BusProvider.getInstance().post(new PartyEvent(partydata, INDEX_ALL_PARTY));
                Log.d("dasd","ok!");
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

    public void writeNewParty(String key, Object data) {
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
