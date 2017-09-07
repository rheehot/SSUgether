package org.collapsed.ssuparty_android.model.contest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContestDB {
    private DatabaseReference mContestRef;

    public ContestDB() {
        this.mContestRef = FirebaseDatabase.getInstance().getReference().child("contest");
    }

    public ArrayList<ContestData> getAllContest() {
        mContestRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<ContestData>> typeIndicator = new GenericTypeIndicator<ArrayList<ContestData>>() {
                };
                ArrayList<ContestData> datas = dataSnapshot.getValue(typeIndicator);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;
    }
}
