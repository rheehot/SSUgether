package org.collapsed.ssuparty_android.ui.account;

public interface AccountContract {
    interface View {

        void showFailedFBLogin();

        void showCanceledFBLogin();
    }

    interface UserActionListener {
    }
}
