package org.collapsed.ssuparty_android.ui.signup;

import android.content.Intent;
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
import org.collapsed.ssuparty_android.model.userinfo.UserInfoDB;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;
import org.collapsed.ssuparty_android.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements SignupContract.View {

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
    @BindView(R.id.signup_major_constraint_txt)
    TextView mMajorConstraintText;
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
    private boolean mNameFiiled = false;
    private boolean mNickFiled = false;
    private boolean mStdNumFilled = false;
    private boolean mMajorFiiled = false;
    private boolean mAgree = false;

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
        getSupportActionBar().setTitle("회원가입");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(view -> finish());

        mSubmitBtn.setVisibility(View.GONE);

        mAgreeCheckBox.setOnCheckedChangeListener((compoundButton, checked) -> {
            mAgree = checked;
            checkSignupAble();
        });

        mNameEditText.addTextChangedListener(new TextWatcher() {
            String curStr;

            @Override
            public void beforeTextChanged(CharSequence inputedString, int i, int i1, int i2) {
                curStr = inputedString.toString();
            }

            @Override
            public void onTextChanged(CharSequence inputedString, int start, int before, int count) {
                if (inputedString.length() > 9) {
                    mNameConstraintText.setText("10/10");
                    mNameConstraintText.setTextColor(Color.RED);
                } else if (inputedString.length() == 0) {
                    mNameFiiled = false;
                } else {
                    mNameFiiled = true;
                    mNameConstraintText.setText(String.valueOf(inputedString.length()) + "/10");
                    mNameConstraintText.setTextColor(Color.BLACK);
                }
                checkSignupAble();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mNickNameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence inputedString, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence inputedString, int start, int before, int count) {
                if (inputedString.length() > 9) {
                    mNickNameConstraintText.setText("10/10");
                    mNickNameConstraintText.setTextColor(Color.RED);
                } else if (inputedString.length() == 0) {
                    mNickFiled = false;
                } else {
                    mNickFiled = true;
                    mNickNameConstraintText.setText(String.valueOf(inputedString.length()) + "/10");
                    mNickNameConstraintText.setTextColor(Color.BLACK);
                }
                checkSignupAble();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mStdnumEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence inputedString, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence inputedString, int start, int before, int count) {
                if (inputedString.length() > 7) {
                    mStdnumConstraintText.setText("8/8");
                    mStdnumConstraintText.setTextColor(Color.RED);
                } else if (inputedString.length() == 0) {
                    mStdNumFilled = false;
                } else {
                    mStdNumFilled = true;
                    mStdnumConstraintText.setText(String.valueOf(inputedString.length()) + "/8");
                    mStdnumConstraintText.setTextColor(Color.BLACK);
                }
                checkSignupAble();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.signup_major));
        mMajorEditText.setAdapter(autoCompleteAdapter);

        mMajorEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence inputedString, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence inputedString, int i, int i1, int i2) {
                if (inputedString.length() > 11) {
                    mMajorConstraintText.setText("12/12");
                    mMajorConstraintText.setTextColor(Color.RED);
                }
                else if (inputedString.length() == 0) {
                    mMajorFiiled = false;
                } else {
                    mMajorFiiled = true;
                    mMajorConstraintText.setText(String.valueOf(inputedString.length()) + "/12");
                    mMajorConstraintText.setTextColor(Color.BLACK);
                }
                checkSignupAble();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSubmitBtn.setOnClickListener(view -> signupFinish());
    }

    public void signupFinish() {
        Intent intent = getIntent();

        String uid = intent.getStringExtra("uid");
        String imgUrl = intent.getStringExtra("imgUrl");
        String email = intent.getStringExtra("email");
        String name = mNameEditText.getText().toString();
        String nickname = mNickNameEditText.getText().toString();
        String major = mMajorEditText.getText().toString();
        String grade = mGradeSpinner.getSelectedItem().toString();
        String studentID = mStdnumEditText.getText().toString();
        long gender = mGenderSpinner.getSelectedItemPosition();

        UserInfoData data = new UserInfoData(uid, email, name, nickname, imgUrl, major, grade, studentID, gender, "", null, null);
        UserInfoDB userDB = new UserInfoDB();
        userDB.writeNewUser(data);

        Intent moveIntent = new Intent(this, MainActivity.class);
        startActivity(moveIntent);
        finish();
    }

    public void checkSignupAble() {
        if (mStdNumFilled && mNickFiled && mNameFiiled && mMajorFiiled && mAgree) {
            mSubmitBtn.setVisibility(View.VISIBLE);
            return;
        }

        mSubmitBtn.setVisibility(View.GONE);
    }
}
