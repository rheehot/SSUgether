package org.collapsed.ssuparty_android.ui.commomlist;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class CommonListPresenter {

    private static final String TAG = CommonListPresenter.class.getSimpleName();

    private CommonListFragment mView;

    public CommonListPresenter(@NonNull CommonListFragment view) {
        this.mView = checkNotNull(view);
    }

}
