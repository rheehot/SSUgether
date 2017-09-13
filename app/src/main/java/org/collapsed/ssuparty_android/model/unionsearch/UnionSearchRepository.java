package org.collapsed.ssuparty_android.model.unionsearch;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.collapsed.ssuparty_android.model.contest.ContestData;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.profile.ProfileData;

import java.util.ArrayList;

public class UnionSearchRepository {
    private static final String TAG = UnionSearchRepository.class.getSimpleName();

    private ArrayList<ProfileData> mfetchedProfiles;
    private ArrayList<ContestData> mfetchedContest;
    private ArrayList<PartyData> mfetchedParty;

    private OnUnionSearchedListener mListener;
    private CharSequence mSearchKeyword;

    public UnionSearchRepository(OnUnionSearchedListener listener, CharSequence searchKeyword) {
        mListener = listener;
        mSearchKeyword = searchKeyword;
    }

    public void fetch() {
        DatabaseReference contestRef = FirebaseDatabase.getInstance().getReference().child("contest");
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference().child("profile");
        DatabaseReference partyRef = FirebaseDatabase.getInstance().getReference().child("all_party");

        contestRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mfetchedContest = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ContestData data = snapshot.getValue(ContestData.class);
                    mfetchedContest.add(data);
                }

                wrappingData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        profileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mfetchedProfiles = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProfileData data = snapshot.getValue(ProfileData.class);
                    mfetchedProfiles.add(data);
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
                mfetchedParty = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PartyData data = snapshot.getValue(PartyData.class);
                    mfetchedParty.add(data);
                }

                wrappingData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void wrappingData() {
        if ((mfetchedParty != null) && (mfetchedProfiles != null) && (mfetchedContest != null)) {
            filteringWithKeyword();
            UnionSearchData wrappedData = new UnionSearchData(mfetchedProfiles, mfetchedParty, mfetchedContest);
            mListener.onUnionSearched(wrappedData);
        }
    }

    private void filteringWithKeyword() {
        ArrayList<PartyData> filteredPartys = new ArrayList<>();
        for (PartyData party : mfetchedParty) {
            if (party.getTitle().contains(mSearchKeyword) || party.getInformation().contains(mSearchKeyword)) {
                filteredPartys.add(party);
            }

//            try {
//                for (String tag : party.getTags()) {
//                    if (tag.contains(mSearchKeyword)) {
//                        filteredPartys.add(party);
//                    }
//                }
//            } catch (Exception e) {
//                Log.e(TAG, e.getMessage());
//            }
        }

        mfetchedParty = filteredPartys;

        ArrayList<ProfileData> filteredProfiles = new ArrayList<>();
        for (ProfileData profile : mfetchedProfiles) {
            try {
                if (profile.getNickName().contains(mSearchKeyword) || profile.getSimpleUserIntro().contains(mSearchKeyword)) {
                    filteredProfiles.add(profile);
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

//            try {
//                for (HashMap<String, String> tag : profile.getTag()) {
//                    if (tag.values().contains(mSearchKeyword)) {
//                        filteredProfiles.add(profile);
//                    }
//                }
//            } catch (Exception e) {
//                Log.e(TAG, e.getMessage());
//            }
        }

        mfetchedProfiles = filteredProfiles;

        ArrayList<ContestData> filteredContests = new ArrayList<>();
        for (ContestData contest : mfetchedContest) {
            if (contest.getTitle().contains(mSearchKeyword)) {
                filteredContests.add(contest);
            }
        }

        mfetchedContest = filteredContests;
    }
}
