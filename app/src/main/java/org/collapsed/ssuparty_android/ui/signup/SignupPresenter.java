package org.collapsed.ssuparty_android.ui.signup;

import android.support.annotation.NonNull;


import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public class SignupPresenter implements SignupContract.UserActionListener, Serializable {

    private SignupActivity mView;

    public SignupPresenter(@NonNull SignupActivity view) {
        this.mView = checkNotNull(view);
    }
}
