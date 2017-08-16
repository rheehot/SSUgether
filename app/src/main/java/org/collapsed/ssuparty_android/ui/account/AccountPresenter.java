package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;


import static com.google.common.base.Preconditions.checkNotNull;

public class AccountPresenter implements AccountContract.UserActionListener {

    private AccountActivity mView;

    public AccountPresenter(@NonNull AccountActivity view) {
        this.mView = checkNotNull(view);
    }

}
