package org.collapsed.ssuparty_android.ui.signup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Signup1Fragment extends Fragment implements SignupContract.View {

    @BindView(R.id.signup_name_edittxt)
    EditText mNameEditText;
    @BindView(R.id.signup_name_constraint_txt)
    TextView mNameConstraintText;
    @BindView(R.id.signup_nickname_edittxt)
    EditText mNickNameEditText;
    @BindView(R.id.signup_nickname_constraint_txt)
    TextView mNickNameConstraintText;
    @BindView(R.id.signup_major_spinner)
    AppCompatSpinner mMajorSpinner;

    private SignupPresenter mPresenter;

    public static Signup1Fragment newInstance(SignupPresenter presenter) {
        Signup1Fragment fragment = new Signup1Fragment();
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
        return inflater.inflate(R.layout.fragment_signup1, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        ButterKnife.bind(this, rootView);

        initView(rootView);
    }

    @Override
    public void initView(View rootView) {
        mNameEditText.addTextChangedListener(new TextWatcher() {
            String curStr;

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                curStr = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 10) {
                    mNameEditText.setText(curStr);
                    mNameEditText.setSelection(start);
                    mNameConstraintText.setTextColor(Color.RED);
                } else {
                    mNameConstraintText.setText(String.valueOf(s.length())+"/10");
                    mNameConstraintText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mNickNameEditText.addTextChangedListener(new TextWatcher() {
            String curStr;

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                curStr = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 10) {
                    mNickNameEditText.setText(curStr);
                    mNickNameEditText.setSelection(start);
                    mNickNameConstraintText.setTextColor(Color.RED);
                } else {
                    mNickNameConstraintText.setText(String.valueOf(s.length())+"/10");
                    mNickNameConstraintText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
