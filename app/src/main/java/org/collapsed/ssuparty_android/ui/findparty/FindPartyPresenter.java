package org.collapsed.ssuparty_android.ui.findparty;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

class FindPartyPresenter implements FindPartyContract.UserActionListener {
    private static final String TAG = FindPartyPresenter.class.getSimpleName();

    private View mDialogView;
    private FindPartyFragment mView;

    public FindPartyPresenter(@NonNull FindPartyFragment view) {
        this.mView = checkNotNull(view);
    }

}
