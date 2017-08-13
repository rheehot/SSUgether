package org.collapsed.ssuparty_android.ui.signup;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.signup_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.signup_name_edittxt)
    EditText mNameEditText;
    @BindView(R.id.signup_name_constraint_txt)
    TextView mNameConstraintText;
    @BindView(R.id.signup_nickname_edittxt)
    EditText mNickNameEditText;
    @BindView(R.id.signup_nickname_constraint_txt)
    TextView mNickNameConstraintText;
    @BindView(R.id.signup_major_edittxt)
    AutoCompleteTextView mMajorEditText;
    @BindView(R.id.signup_grade_spinner)
    AppCompatSpinner mGradeSpinner;
    @BindView(R.id.signup_stdnum_edittxt)
    EditText mStdnumEditText;
    @BindView(R.id.signup_stdnum_constraint_txt)
    TextView mStdnumConstraintText;
    @BindView(R.id.signup_gender_spinner)
    AppCompatSpinner mGenderSpinner;

    @BindView(R.id.signup_private_agree_checkbox)
    CheckBox mAgreeCheckBox;
    @BindView(R.id.signup_submit_btn)
    Button mSubmitBtn;

    private SignupPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mPresenter = new SignupPresenter(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                    mNameConstraintText.setText(String.valueOf(s.length()) + "/10");
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
                    mNickNameConstraintText.setText(String.valueOf(s.length()) + "/10");
                    mNickNameConstraintText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
                    mStdnumConstraintText.setText(String.valueOf(s.length()) + "/8");
                    mStdnumConstraintText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.signup_major));
        mMajorEditText.setAdapter(autoCompleteAdapter);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 요청
            }
        });
    }
}
