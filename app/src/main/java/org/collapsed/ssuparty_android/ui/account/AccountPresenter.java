package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountPresenter implements AccountContract.UserActionListener {

    private AccountActivity mView;

    private SessionCallback callback;

    public AccountPresenter(@NonNull AccountActivity view) {
        this.mView = checkNotNull(view);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    public boolean handleActivityResultWithCurrentSession(int requestCode, int resultCode, Intent data) {
        return Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void removeSessionCallback() {
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            mView.redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }
}
