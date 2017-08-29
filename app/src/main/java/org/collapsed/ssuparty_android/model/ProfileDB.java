package org.collapsed.ssuparty_android.model;

import android.net.Uri;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;

import org.collapsed.ssuparty_android.event.BusProvider;
import org.collapsed.ssuparty_android.event.profile.ImageEvent;
import org.collapsed.ssuparty_android.event.profile.IntroEvent;
import org.collapsed.ssuparty_android.event.profile.TagEvent;

import java.util.List;

public class ProfileDB {

    private static final String DB_PROFILE_KEY = "profile";

    private DatabaseReference mRootDBRef, mProfileDBRef, mPersonalTagsDBRef, mPersonalIntroDBRef, mPersonalImageDBRef;
    private Bus mEventBus;

    //실험용, 나중에는 로컬에 저장된 user id를 받아와서 이용.
    String personalId = "kingjihoon123";

    public ProfileDB() {
        initModel();
    }

    public void initModel() {
        mRootDBRef = FirebaseDatabase.getInstance().getReference();
        mProfileDBRef = mRootDBRef.child(DB_PROFILE_KEY);
        mPersonalTagsDBRef = mProfileDBRef.child(personalId).child("tags");
        mPersonalIntroDBRef = mProfileDBRef.child(personalId).child("introduction");
        mPersonalImageDBRef = mProfileDBRef.child(personalId).child("image");

        mEventBus = BusProvider.getInstance();

        mPersonalIntroDBRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String introductionData = dataSnapshot.getValue(String.class);

                //call setNewIntroduction(...) in profileFragment;
                mEventBus.post(new IntroEvent(introductionData));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mPersonalTagsDBRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> tagsData = (List<String>) dataSnapshot.getValue();

                //call setNewTags(...) in profileFragment;
                mEventBus.post(new TagEvent(tagsData));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mPersonalImageDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uriData = dataSnapshot.getValue(String.class);

                //call setNewImage(...) in profileFragment;
                mEventBus.post(new ImageEvent(Uri.parse(uriData)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void writeNewUser(String userId, Object profileData) {
        mProfileDBRef.child(userId).setValue(profileData);
    }

    public void writeNewTags(String userId, List<String> tags) {
        mProfileDBRef.child(userId).child("tags").setValue(tags);
    }

    public void writeNewIntroduction(String userId, String introduction) {
        mProfileDBRef.child(userId).child("introduction").setValue(introduction);
    }

    public void writeNewProfileImage(Uri imageUri) {
        mProfileDBRef.child(personalId).child("image").setValue(imageUri.toString());
    }

}
