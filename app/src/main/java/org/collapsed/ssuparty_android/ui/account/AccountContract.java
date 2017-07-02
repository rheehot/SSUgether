package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;

public interface AccountContract {
    interface View {
        void redirectSignupActivity();
    }

    interface UserActionListener {
        boolean handleActivityResultWithCurrentSession(int requestCode, int resultCode, Intent data);

        void removeSessionCallback();
    }
}
