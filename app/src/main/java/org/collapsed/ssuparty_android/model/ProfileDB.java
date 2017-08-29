package org.collapsed.ssuparty_android.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;

import org.collapsed.ssuparty_android.event.BusProvider;
import org.collapsed.ssuparty_android.event.profile.IntroEvent;
import org.collapsed.ssuparty_android.event.profile.TagEvent;

import java.util.List;

public class ProfileDB {

    private static final String DB_PROFILE_KEY = "profile";

    private static final int DB_PROFILE_INTRO_MODE = 1;
    private static final int DB_PROFILE_TAG_MODE = 2;
    private static final int DB_PROFILE_IMAGE_MODE = 3;

    private DatabaseReference mRootRef, mProfileRef, mPersonalTagsRef, mPersonalIntroRef;
    private Bus mEventBus;

    //실험용, 나중에는 로컬에 저장된 user id를 받아와서 이용.
    String personalId = "kingjihoon123";

    public ProfileDB() {
        initModel();
    }

    public void initModel() {
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mProfileRef = mRootRef.child(DB_PROFILE_KEY);
        mPersonalTagsRef = mProfileRef.child(personalId).child("tags");
        mPersonalIntroRef = mProfileRef.child(personalId).child("introduction");

        mEventBus = BusProvider.getInstance();

        mPersonalIntroRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String introductionData = dataSnapshot.getValue(String.class);
                mEventBus.post(new IntroEvent(introductionData));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mPersonalTagsRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> tagsData = (List<String>) dataSnapshot.getValue();
                mEventBus.post(new TagEvent(tagsData));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void writeNewUser(String userId, Object profileData) {
        mProfileRef.child(userId).setValue(profileData);
    }

    public void writeNewTags(String userId, List<String> tags) {
        mProfileRef.child(userId).child("tags").setValue(tags);
    }

    public void writeNewIntroduction(String userId, String introduction) {
        mProfileRef.child(userId).child("introduction").setValue(introduction);
    }
}
