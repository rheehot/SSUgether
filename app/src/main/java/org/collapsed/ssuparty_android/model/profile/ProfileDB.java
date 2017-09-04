package org.collapsed.ssuparty_android.model.profile;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.collapsed.ssuparty_android.ui.profile.ProfilePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileDB {

    private static final String TAG = ProfileDB.class.getSimpleName();
    private static final String DB_PROFILE_KEY = "profile";

    private ProfilePresenter mProfilePresenter;
    private DatabaseReference mRootDBRef, mProfileDBRef, mTagsDBRef, mIntroDBRef;
    private StorageReference mRootStorageRef, mImageStorageRef;
    private String mFilename, mUserUid;

    public ProfileDB(ProfilePresenter presenter) {
        mProfilePresenter = presenter;
        mUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        initModel();
    }

    public void initModel() {
        mRootDBRef = FirebaseDatabase.getInstance().getReference();
        mProfileDBRef = mRootDBRef.child(DB_PROFILE_KEY);
        mTagsDBRef = mProfileDBRef.child(mUserUid).child("tags");
        mIntroDBRef = mProfileDBRef.child(mUserUid).child("intro");


        mFilename = mUserUid + "_profile_image.png";
        mRootStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ssugether.appspot.com/");
        mImageStorageRef = mRootStorageRef.child("images/" + mFilename);
    }

    public void readProfileImage() {
        mImageStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String imageUrl = uri.toString();
                mProfilePresenter.updateImageData(imageUrl);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });
    }

    public void readProfileIntro() {
        mIntroDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String introText = dataSnapshot.getValue(String.class);
                mProfilePresenter.updateIntroData(introText);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void readProfieTagList() {
        mTagsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> tagList = (List<String>) dataSnapshot.getValue();

                if (tagList != null) {
                    mProfilePresenter.updateTagListData(tagList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void writeProfileImage(Uri imageUri) {
        Uri file = imageUri;
        mImageStorageRef = mRootStorageRef.child("images/" + mFilename);
        UploadTask uploadTask = mImageStorageRef.putFile(file);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //프로필 리스트 업데이트 관련 코딩 작업
            }
        });
    }

    public void writeIntroduction(String introText) {
        mProfileDBRef.child(mUserUid).child("intro").setValue(introText);
    }

    public void writeTagList(String[] tagList) {
        mProfileDBRef.child(mUserUid).child("tags").setValue(new ArrayList<String>(Arrays.asList(tagList)));

    }
}
