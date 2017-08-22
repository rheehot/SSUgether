package org.collapsed.ssuparty_android.ui.partylist;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyListPresenter {

    private static final String TAG = PartyListPresenter.class.getSimpleName();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private PartyListFragment mView;

    public PartyListPresenter(@NonNull PartyListFragment view) {
        this.mView = checkNotNull(view);
    }

    public String getUserProfileImageUrl() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            Log.d("login","로그인필요!");
            return "";
        } else {
            String username = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                String photoUrl = mFirebaseUser.getPhotoUrl().toString();
                return photoUrl;
            }
            else{
                return "";
            }
        }
    }
}
