package org.collapsed.ssuparty_android.ui.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.collapsed.ssuparty_android.R;

public class Signup2Fragment extends Fragment implements SignupContract.View {

    private SignupPresenter mPresenter;

    public static Signup2Fragment newInstance(SignupPresenter presenter) {
        Signup2Fragment fragment = new Signup2Fragment();
        Bundle args = new Bundle();
        args.putSerializable("SignupPresenter", presenter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPresenter = (SignupPresenter) getArguments().getSerializable("SignupPresenter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup2, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        initView(rootView);
    }

    @Override
    public void initView(View rootView) {

    }
}
