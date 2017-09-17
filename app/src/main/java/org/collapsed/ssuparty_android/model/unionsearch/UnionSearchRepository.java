package org.collapsed.ssuparty_android.model.unionsearch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.collapsed.ssuparty_android.model.contest.ContestData;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;

import java.util.ArrayList;

public class UnionSearchRepository {
    private static final String TAG = UnionSearchRepository.class.getSimpleName();

    private ArrayList<UserInfoData> mfetchedProfiles;
    private ArrayList<ContestData> mfetchedContest;
    private ArrayList<PartyData> mfetchedParty;

    private OnUnionSearchedListener mListener;
    private CharSequence mSearchKeyword;

    public UnionSearchRepository(OnUnionSearchedListener listener, CharSequence searchKeyword) {
        mListener = listener;
        mSearchKeyword = searchKeyword;
    }

    public void fetch() {
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference().child("users");
        DatabaseReference partyRef = FirebaseDatabase.getInstance().getReference().child("allParty");

        profileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isAdded = false;
                mfetchedProfiles = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserInfoData data = snapshot.getValue(UserInfoData.class);
                    if (data.getName().contains(mSearchKeyword) || data.getIntro().contains(mSearchKeyword)) {
                        isAdded = true;
                        mfetchedProfiles.add(data);
                    }

                    if (data.getTags() != null) {
                        for (String tag : data.getTags()) {
                            if (tag.contains(mSearchKeyword) && !isAdded) {
                                mfetchedProfiles.add(data);
                            }
                        }
                    }
                }

                wrappingData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        partyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isAdded = false;
                mfetchedParty = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PartyData data = snapshot.getValue(PartyData.class);
                    if (data.getTitle().contains(mSearchKeyword) || data.getDescription().contains(mSearchKeyword)) {
                        mfetchedParty.add(data);
                    }

                    if (data.getTags()!=null) {
                        for (String tag : data.getTags()) {
                            if (tag.contains(mSearchKeyword) && !isAdded) {
                                mfetchedParty.add(data);
                            }
                        }
                    }
                }

                wrappingData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void wrappingData() {
        if ((mfetchedParty != null) && (mfetchedProfiles != null)) {
            UnionSearchData wrappedData = new UnionSearchData(mfetchedProfiles, mfetchedParty);
            mListener.onUnionSearched(wrappedData);
        }
    }
}
