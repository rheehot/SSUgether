package org.collapsed.ssuparty_android.model.party;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;
import org.collapsed.ssuparty_android.ui.partydetail.PartyDetailPresenter;

import java.util.HashMap;
import java.util.Map;

public class PartyDB {

    private static final String DB_ALL_PARTY_KEY = "testParty";

    private static OnPartyDataFetchedListener mPresenter;
    private static DatabaseReference mRootRef, mAllPartyRef, mCreatedPartyRef;
    private static StorageReference mRootStorageRef;

    public static void fetchAllParty(OnPartyDataFetchedListener listener) {
        mPresenter = listener;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        mAllPartyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PartyData partyData = dataSnapshot.getValue(PartyData.class);
                mPresenter.onFetched(partyData);
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

    public static void writeNewParty(PartyData partyData) {
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        String partyKey = mAllPartyRef.push().getKey();
        partyData.setPartyID(partyKey);
        mAllPartyRef.child(partyKey).setValue(partyData);
    }

    public static void writePartyImage(String partyId, Uri imageUri) {
        mRootRef = FirebaseDatabase.getInstance().getReference();

        mCreatedPartyRef = mRootRef.child(DB_ALL_PARTY_KEY).child(partyId);

        mRootStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ssugether.appspot.com/");

        Uri file = imageUri;
        String mFilename = partyId + "_party_image.png";
        StorageReference mImageStorageRef = mRootStorageRef.child("images/" + mFilename);

        UploadTask uploadTask = mImageStorageRef.putFile(file);

        uploadTask.addOnFailureListener(exception -> {
        }).addOnSuccessListener(taskSnapshot -> {

            @SuppressWarnings("VisibleForTests")
            String downloadUrl = taskSnapshot.getDownloadUrl().toString();
            mCreatedPartyRef.child("imageUrl").setValue(downloadUrl);
        });
    }

    public static void readPartyImage(String partyId, PartyDetailPresenter presenter) {
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mCreatedPartyRef = mRootRef.child(DB_ALL_PARTY_KEY).child(partyId);
        mCreatedPartyRef.child("imageUrl").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imageUrl = dataSnapshot.getValue(String.class);

                if (imageUrl != null) {
                    presenter.updatePartyImage(imageUrl);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void executeFetch(OnPartyDataFetchedListener listener, String uid) {
        mPresenter = listener;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        mAllPartyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                boolean isAdded = false;
                PartyData partyData = dataSnapshot.getValue(PartyData.class);
                if (partyData.getParticipants() != null) {
                    for (String fetchedUid : partyData.getParticipants()) {
                        if (fetchedUid.equals(uid)) {
                            isAdded = true;
                            mPresenter.onFetched(partyData);
                        }
                    }
                }

                if (!isAdded && partyData.getFounder().equals(uid)) {
                    mPresenter.onFetched(partyData);
                }
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

    public static void sendApplyRequest(OnApplyStatusChangeListener listener, PartyData partyData) {
        // 비 검증 함수
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAllPartyRef = mRootRef.child("testParty").child(partyData.getPartyID());
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        partyData.getApplyMemberStatus().add(new ApplyMemberStatus(uid, 2));
        mAllPartyRef.setValue(partyData);

        listener.onApplyChanged(partyData);
    }

    public static void convertUidToUserInfo(OnConvertParticipantListener listener, String uid) {
        mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userInfoRef = mRootRef.child("users").child(uid);
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInfoData data = dataSnapshot.getValue(UserInfoData.class);
                listener.onConvertParticipant(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface OnPartyDataFetchedListener {
        void onFetched(PartyData data);
    }

    public interface OnApplyStatusChangeListener {
        void onApplyChanged(PartyData changedPartyData);
    }

    public interface OnConvertParticipantListener {
        void onConvertParticipant(UserInfoData info);
    }
}
