package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.collapsed.ssuparty_android.model.userinfo.UserInfoDB;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;
import org.collapsed.ssuparty_android.ui.main.MainActivity;
import org.collapsed.ssuparty_android.ui.signup.SignupActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountPresenter implements AccountContract.UserActionListener, UserInfoDB.OnUserInfoFetchListener {

    private static final String TAG = AccountPresenter.class.getSimpleName();
    private AccountActivity mView;
    private FirebaseAuth mAuth;

    public AccountPresenter(@NonNull AccountActivity view) {
        this.mView = checkNotNull(view);
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void setFacebookLoginCallback(LoginButton loginButton, CallbackManager callbackManager) {
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mView.showProgress();
                AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(mView, task -> {
                            if (task.isSuccessful()) {
                                checkAlreadySignUp();
                            } else {
                                mView.showFailedFBLogin();
                            }
                        });
            }

            @Override
            public void onCancel() {
                mView.showCanceledFBLogin();
            }

            @Override
            public void onError(FacebookException error) {
                mView.showFailedFBLogin();
            }
        });
    }

    private void checkAlreadySignUp() {
        FirebaseUser user = mAuth.getCurrentUser();
        try {
            UserInfoDB db = new UserInfoDB(this, user.getUid());
            db.fetchUserProfileData();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void moveToNextActivity(UserInfoData data) {
        FirebaseUser user = mAuth.getCurrentUser();
        Intent intent;
        if (data != null) {
            intent = new Intent(mView, MainActivity.class);
        } else {
            intent = new Intent(mView, SignupActivity.class);
            if (user != null) {
                intent.putExtra("email", user.getEmail());
                intent.putExtra("imgUrl", user.getPhotoUrl());
                intent.putExtra("uid", user.getUid());
            }
        }

        mView.hideProgress();
        mView.startActivity(intent);
        mView.finish();
    }

    @Override
    public void onFetched(UserInfoData data) {
        moveToNextActivity(data);
    }
}
