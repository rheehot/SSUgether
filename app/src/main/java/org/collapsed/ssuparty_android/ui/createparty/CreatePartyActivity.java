package org.collapsed.ssuparty_android.ui.createparty;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.customview.TagsEditText;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CreatePartyActivity extends AppCompatActivity implements CreatePartyContract.View {

    private static final String TEXT_COLOR = "#44394d";
    public static final int CREATE_ACTIVITY_REQUEST_CODE = 1;

    @BindView(R.id.createparty_partycontent_layout)
    LinearLayout mContentLayout;
    @BindView(R.id.createparty_confirm_btn)
    Button mInfoConfirmBtn;
    @BindView(R.id.createparty_cancel_party_btn)
    ImageButton mPartyCancelBtn;
    @BindView(R.id.createparty_register_party_btn)
    Button mPartyRegisterBtn;
    @BindView(R.id.createparty_title_edt)
    EditText mTitleEditText;
    @BindView(R.id.createparty_membernum_edt)
    EditText mMemberNumEditText;
    @BindView(R.id.createparty_information_edt)
    EditText mInfoEditText;
    @BindView(R.id.createparty_toolbar_title_txt)
    TextView mToolbarTitleText;
    @BindView(R.id.createparty_select_deadline_txt)
    TextView mDeadlineText;
    @BindView(R.id.createparty_tag_help_txt)
    TextView mTagHelpText;
    @BindView(R.id.createparty_membernum_help_txt)
    TextView mMemberNumHelpText;
    @BindView(R.id.createparty_title_constraint_txt)
    TextView mTitleConstraintText;
    @BindView(R.id.createparty_information_constraint_txt)
    TextView mInfoConstraintText;
    @BindView(R.id.createparty_tag_constraint_txt)
    TextView mTagConstraintText;
    @BindView(R.id.createparty_select_category_spn)
    Spinner mCategorySelectSpinner;
    @BindView(R.id.createparty_tag_edt)
    TagsEditText mTagEditText;

    private View.OnClickListener mClickListner;
    private View.OnFocusChangeListener mFocusListner;
    private View.OnTouchListener mTouchListner;
    private DatePickerDialog.OnDateSetListener mDateLisner;

    private CreatePartyPresenter mPresenter;
    private InputMethodManager mImManager;
    private ArrayAdapter mCategoryAdapter;
    private Intent mIntentForResult;
    private Calendar mDateData;

    private Unbinder mUnbinder;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createparty);
        ButterKnife.bind(this);

        mPresenter = new CreatePartyPresenter(this);
        mUnbinder = ButterKnife.bind(this);

        initView();
    }

    public void initView() {
        mCategoryAdapter = ArrayAdapter.createFromResource(CreatePartyActivity.this, R.array.party_category,
                R.layout.item_spinner);

        mImManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mToolbarTitleText.setFocusableInTouchMode(true);
        mCategorySelectSpinner.setAdapter(mCategoryAdapter);

        mTagEditText.setTagsWithSpacesEnabled(true);
        mTagEditText.setThreshold(1);

        setVisibility();

        mTitleEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 12) {
                    mTitleConstraintText.setText("13/13");
                    mTitleConstraintText.setTextColor(Color.RED);
                } else {
                    mTitleConstraintText.setText(String.valueOf(s.length()) + "/13");
                    mTitleConstraintText.setTextColor(Color.parseColor(TEXT_COLOR));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mInfoEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 59) {
                    mInfoConstraintText.setText("60/60");
                    mInfoConstraintText.setTextColor(Color.RED);
                } else {
                    mInfoConstraintText.setText(String.valueOf(s.length()) + "/60");
                    mInfoConstraintText.setTextColor(Color.parseColor(TEXT_COLOR));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mTagEditText.addTextChangedListener(new TextWatcher() {
            int beforeSize;
            List<String> tags;

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                beforeSize = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tags = mTagEditText.getTags();
                if (tags.size() > 3 && s.length() > beforeSize) {
                    mTagConstraintText.setText("4/4");
                    mTagConstraintText.setTextColor(Color.RED);
                    mTagEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(s.length())});
                } else {
                    if (s.length() < beforeSize && tags.size() > 0)
                        tags.remove(tags.size() - 1);
                    mTagConstraintText.setText(String.valueOf(tags.size()) + "/4");
                    mTagConstraintText.setTextColor(Color.parseColor(TEXT_COLOR));
                    mTagEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mTouchListner = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()) {
                    case R.id.createparty_partycontent_layout:
                        hideVirtualKeyboard(mContentLayout);
                        break;

                    case R.id.createparty_select_category_spn:
                        hideVirtualKeyboard(mCategorySelectSpinner);
                        break;

                    default:
                        break;
                }
                return false;
            }
        };

        setTouchListner(mContentLayout);
        setTouchListner(mCategorySelectSpinner);

        mClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.createparty_register_party_btn:
                        if (mPresenter.checkInputData(mTitleEditText, mCategorySelectSpinner,
                                mDeadlineText, mMemberNumEditText, mInfoEditText, mTagEditText)) {
                            mIntentForResult = mPresenter.putDataToIntent();
                            setResult(CreatePartyActivity.RESULT_OK, mIntentForResult);
                            finish();
                        } else {
                            Toast.makeText(CreatePartyActivity.this,
                                    "입력되지 않은 정보가 있습니다!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.createparty_confirm_btn:
                        hideVirtualKeyboard(mInfoConfirmBtn);
                        break;

                    case R.id.createparty_cancel_party_btn:
                        finish();
                        break;

                    case R.id.createparty_select_deadline_txt:
                        showDatePickerDialog();
                        hideVirtualKeyboard(mDeadlineText);
                        break;

                    default:
                        break;
                }
            }
        };

        setClickListner(mInfoConfirmBtn);
        setClickListner(mPartyCancelBtn);
        setClickListner(mDeadlineText);
        setClickListner(mPartyRegisterBtn);

        mFocusListner = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean getFocus) {
                if (getFocus) {
                    mPartyCancelBtn.setVisibility(View.GONE);
                    mInfoConfirmBtn.setVisibility(View.VISIBLE);
                    switch (view.getId()) {
                        case R.id.createparty_tag_edt:
                            mTagHelpText.setVisibility(View.VISIBLE);
                            break;
                        case R.id.createparty_membernum_edt:
                            mMemberNumHelpText.setText("최대 100명까지 모집할 수 있어요!");
                            mMemberNumHelpText.setVisibility(View.VISIBLE);
                            break;
                        default:
                            break;
                    }
                } else {
                    mPartyCancelBtn.setVisibility(View.VISIBLE);
                    mInfoConfirmBtn.setVisibility(View.GONE);
                    mTagHelpText.setVisibility(View.GONE);
                    mMemberNumHelpText.setVisibility(View.GONE);
                }
            }
        };

        setFocusListner(mTitleEditText);
        setFocusListner(mMemberNumEditText);
        setFocusListner(mInfoEditText);
        setFocusListner(mTagEditText);
    }

    public void hideVirtualKeyboard(View view) {
        mToolbarTitleText.requestFocus();
        mImManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showDatePickerDialog() {
        mDateData = mPresenter.getCalenderData();

        mDateLisner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String dateText = mPresenter.checkCorrectDeadline(year, month, day);
                mDeadlineText.setText(dateText);
                mDeadlineText.setTextColor(Color.parseColor(TEXT_COLOR));
            }
        };

        new DatePickerDialog(CreatePartyActivity.this,
                mDateLisner, mDateData.get(Calendar.YEAR), mDateData.get(Calendar.MONTH),
                mDateData.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void setVisibility() {
        mInfoConfirmBtn.setVisibility(View.GONE);
        mTagHelpText.setVisibility(View.GONE);
        mMemberNumHelpText.setVisibility(View.GONE);
    }

    public void setMemberNumTextByException(String errorText) {
        mMemberNumHelpText.setText(errorText);
        mMemberNumHelpText.setVisibility(View.VISIBLE);
        mMemberNumEditText.setText("");
    }

    public void setTouchListner(View view) {
        view.setOnTouchListener(mTouchListner);
    }

    public void setClickListner(View view) {
        view.setOnClickListener(mClickListner);
    }

    public void setFocusListner(View view) {
        view.setOnFocusChangeListener(mFocusListner);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
