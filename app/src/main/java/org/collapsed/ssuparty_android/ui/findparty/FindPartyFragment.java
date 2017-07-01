package org.collapsed.ssuparty_android.ui.findparty;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FindPartyFragment extends BaseFragment implements FindPartyContract.View {
    private static final String TAG = FindPartyFragment.class.getSimpleName();

    private FindPartyPresenter mPresenter;

    public static FindPartyFragment newInstance() {
        FindPartyFragment fragment = new FindPartyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_findparty, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mPresenter = new FindPartyPresenter(this);
    }
}
