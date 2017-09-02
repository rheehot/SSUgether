package org.collapsed.ssuparty_android.model.userinfo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.collapsed.ssuparty_android.ui.account.AccountPresenter;

public class UserInfoDB {
    private DatabaseReference mUserInfoRef;
    private AccountPresenter mPresenter;

    public UserInfoDB(AccountPresenter presenter) {
        this.mUserInfoRef = FirebaseDatabase.getInstance().getReference().child("users");
        this.mPresenter = presenter;
    }

    public UserInfoDB() {
        this.mUserInfoRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void writeNewUser(UserInfoData userInfoData) {
        mUserInfoRef.child(userInfoData.uid).setValue(userInfoData);
    }

    public void findUserProfileData(String uid) {
        if (mPresenter != null) {
            mUserInfoRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserInfoData data = dataSnapshot.getValue(UserInfoData.class);
                    mPresenter.moveToNextActivity(data);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
