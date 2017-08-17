package org.collapsed.ssuparty_android.ui.partylist;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.collapsed.ssuparty_android.adapter.PartyListAdapter;
import org.collapsed.ssuparty_android.adapter.ProfileListAdapter;
import org.collapsed.ssuparty_android.model.PartyData;
import org.collapsed.ssuparty_android.model.ProfileData;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class PartyListPresenter implements PartyListContract.OnChangeDataListListner {

    private static final String TAG = PartyListPresenter.class.getSimpleName();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;

    private PartyListFragment mView;

    public PartyListPresenter(@NonNull PartyListFragment view) {
        this.mView = checkNotNull(view);
    }

    public void setNewDataToAdapter(ArrayList<PartyData> dataSet, PartyListAdapter adapter,
                                    PartyData object) {
        dataSet.add(object);
        adapter.addItem(dataSet);
    }

    public void setNewDataToAdapter(ArrayList<ProfileData> dataSet, ProfileListAdapter adapter,
                                    ProfileData object) {
        dataSet.add(object);
        adapter.addItem(dataSet);
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
