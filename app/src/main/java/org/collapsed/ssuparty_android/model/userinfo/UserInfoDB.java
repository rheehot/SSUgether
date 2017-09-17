package org.collapsed.ssuparty_android.model.userinfo;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;

public class UserInfoDB {
    private DatabaseReference mUserInfoRef;
    private OnUserInfoFetchListener mPresenter;
    private StorageReference mRootStorageRef;

    private String mUid;

    public UserInfoDB(OnUserInfoFetchListener presenter, @Nullable String uid) {
        this.mUserInfoRef = FirebaseDatabase.getInstance().getReference().child("users");
        this.mRootStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ssugether.appspot.com/");
        this.mPresenter = presenter;
        this.mUid = uid;
    }

    public UserInfoDB() {
        this.mUserInfoRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void writeNewUser(UserInfoData userInfoData) {
        mUserInfoRef.child(userInfoData.uid).setValue(userInfoData);
    }

    public void fetchUserProfileData() {
        if (mPresenter != null) {
            mUserInfoRef.child(mUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserInfoData data = dataSnapshot.getValue(UserInfoData.class);
                    mPresenter.onFetched(data);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void writeProfileImage(Uri imageUri) {
        Uri file = imageUri;
        String mFilename = mUid + "_profile_image.png";
        StorageReference mImageStorageRef = mRootStorageRef.child("images/" + mFilename);
        UploadTask uploadTask = mImageStorageRef.putFile(file);
        uploadTask.addOnFailureListener(exception -> {
        }).addOnSuccessListener(taskSnapshot -> {
            //프로필 리스트 업데이트 관련 코딩 작업
        });
    }

    public void writeIntroduction(String introText) {
        DatabaseReference introDBRef = mUserInfoRef.child(mUid).child("intro");
        introDBRef.setValue(introText);
    }

    public void writeTagList(String[] tagList) {
        DatabaseReference tagsDBRef = mUserInfoRef.child(mUid).child("tags");
        tagsDBRef.setValue(new ArrayList<>(Arrays.asList(tagList)));
    }

    public interface OnUserInfoFetchListener {
        void onFetched(UserInfoData data);
    }
}
