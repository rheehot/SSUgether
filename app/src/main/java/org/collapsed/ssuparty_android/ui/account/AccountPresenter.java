package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountPresenter implements AccountContract.UserActionListener {

    private AccountActivity mView;
    private FirebaseAuth mFirebaseAuth;

    public AccountPresenter(@NonNull AccountActivity view) {
        this.mView = checkNotNull(view);
    }

    public void setFacebookLoginCallback(LoginButton loginButton, CallbackManager callbackManager) {
        mFirebaseAuth = FirebaseAuth.getInstance();

        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    public void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(mView, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mView.redirectSignupActivity();

                        if (!task.isSuccessful()) {
                            Log.d("error","auth fail");
                        }
                    }
                });
    }
}
