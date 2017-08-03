package org.collapsed.ssuparty_android.ui.splash;

interface SplashContract {
    interface View {

    }

    interface UserActionListener {

        void startActivityDependOnLoginState();
    }
}
