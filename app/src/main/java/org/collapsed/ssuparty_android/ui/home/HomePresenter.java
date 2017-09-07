package org.collapsed.ssuparty_android.ui.home;

import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.model.unionsearch.OnUnionSearchedListener;
import org.collapsed.ssuparty_android.model.unionsearch.UnionSearchData;
import org.collapsed.ssuparty_android.model.unionsearch.UnionSearchRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class HomePresenter implements HomeContract.UserActionListener, OnUnionSearchedListener {
    private static final String TAG = HomePresenter.class.getSimpleName();
    private HomeFragment mView;
    private UnionSearchData fetchedData;

    public HomePresenter(@NonNull HomeFragment view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public void performSearch(CharSequence searchKeyword) {
        UnionSearchRepository repo = new UnionSearchRepository(this, searchKeyword);
        repo.fetch();
    }

    @Override
    public void onUnionSearched(UnionSearchData data) {
        this.fetchedData = data;
    }
}
