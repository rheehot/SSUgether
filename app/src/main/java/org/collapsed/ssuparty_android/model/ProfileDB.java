package org.collapsed.ssuparty_android.model;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.otto.Bus;

import org.collapsed.ssuparty_android.event.BusProvider;
import org.collapsed.ssuparty_android.event.profile.FirstProfileEvent;
import org.collapsed.ssuparty_android.event.profile.ImageEvent;
import org.collapsed.ssuparty_android.event.profile.IntroEvent;
import org.collapsed.ssuparty_android.event.profile.TagEvent;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import java.util.List;

public class ProfileDB {

    private static final String DB_PROFILE_KEY = "profile";

    private DatabaseReference mRootDBRef, mProfileDBRef, mPersonalTagsDBRef, mPersonalIntroDBRef;
    private StorageReference mRootStorageRef, mImageStorageRef;
    private Bus mEventBus;
    private String filename;

    //테스트용 ID, 나중에는 로컬에 저장된 user id를 받아와서 이용.
    String personalId = "kingjihoon123";

    public ProfileDB() {
        initModel();
    }

    public void initModel() {
        mRootDBRef = FirebaseDatabase.getInstance().getReference();
        mProfileDBRef = mRootDBRef.child(DB_PROFILE_KEY);
        mPersonalTagsDBRef = mProfileDBRef.child(personalId).child("tags");
        mPersonalIntroDBRef = mProfileDBRef.child(personalId).child("intro");

        filename = personalId+"_profile_image.png";
        mRootStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ssugether.appspot.com/");
        mImageStorageRef = mRootStorageRef.child("images/"+filename);
    }

    public void writeNewUser(String userId, Object profileData) {
        mProfileDBRef.child(userId).setValue(profileData);
    }

    public void writeNewTags(String userId, List<String> tags) {
        mProfileDBRef.child(userId).child("tags").setValue(tags);
    }

    public void writeNewIntroduction(String userId, String introduction) {
        mProfileDBRef.child(userId).child("intro").setValue(introduction);
    }

    //추후에 프로필 리스트에서 업데이트 기능 구현할 때 onSuccess 부분 다시 작성
    public void writeNewProfileImage(Uri imageUri) {
        Uri file = imageUri;
        StorageReference reference = mRootStorageRef.child("images/"+filename);
        UploadTask uploadTask = reference.putFile(file);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                /*@SuppressWarnings("VisibleForTests") String url = taskSnapshot.getDownloadUrl().toString();
                setImageUrl(url);*/
                //mEventBus.post(new ImageEvent(url));
            }
        });
    }

    public void loadImageUrl() {
        StorageReference reference = mRootStorageRef.child("images/"+filename);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                
                //call inflateProfileImageInFirstTime(...) Method in profileFragment;
                mEventBus.post(new FirstProfileEvent(uri.toString()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });
    }
}
