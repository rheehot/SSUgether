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

public class Signup2Fragment extends Fragment implements SignupContract.View {

    @BindView(R.id.signup_grade_spinner)
    AppCompatSpinner mGradeSpinner;
    @BindView(R.id.signup_stdnum_edittxt)
    EditText mStdnumEditText;
    @BindView(R.id.signup_stdnum_constraint_txt)
    TextView mStdnumConstraintText;
    @BindView(R.id.signup_gender_spinner)
    AppCompatSpinner mGenderSpinner;

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
        ButterKnife.bind(this, rootView);

        initView(rootView);
    }

    @Override
    public void initView(View rootView) {
        mStdnumEditText.addTextChangedListener(new TextWatcher() {
            String curStr;

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                curStr = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 8) {
                    mStdnumEditText.setText(curStr);
                    mStdnumEditText.setSelection(start);
                    mStdnumConstraintText.setTextColor(Color.RED);
                } else {
                    mStdnumConstraintText.setText(String.valueOf(s.length())+"/8");
                    mStdnumConstraintText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
