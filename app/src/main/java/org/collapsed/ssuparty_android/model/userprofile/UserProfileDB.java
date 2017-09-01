package org.collapsed.ssuparty_android.model.userprofile;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.collapsed.ssuparty_android.ui.account.AccountPresenter;

public class UserProfileDB {
    private DatabaseReference mUserProfileRef;
    private AccountPresenter mPresenter;

    public UserProfileDB(AccountPresenter presenter) {
        this.mUserProfileRef = FirebaseDatabase.getInstance().getReference().child("users");
        this.mPresenter = presenter;
    }

    public UserProfileDB() {
        this.mUserProfileRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void writeNewUser(UserProfileData userProfileData) {
        mUserProfileRef.child(userProfileData.uid).setValue(userProfileData);
    }

    public void findUserProfileData(String uid) {
        if (mPresenter != null) {
            mUserProfileRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserProfileData data = dataSnapshot.getValue(UserProfileData.class);
                    mPresenter.moveToNextActivity(data);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
