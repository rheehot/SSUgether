package org.collapsed.ssuparty_android.ui.splash;

import android.content.Intent;

interface SplashContract {
    interface View {

    }

    interface UserActionListener {

        Intent getIntentForNextActivity();
    }
}
