package org.collapsed.ssuparty_android.ui.createparty;


import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.customview.TagsEditText;

import java.util.Calendar;
import java.util.List;

public class CreatePartyActivity extends AppCompatActivity implements CreatePartyContract.View {

    private CreatePartyPresenter mPresenter;

    private LinearLayout mContentLayout;

    private Button mPartyCancelBtn, mPartyRegisterBtn, mInfoConfirmBtn;
    private EditText mTitleEditText, mMemberNumEditText, mInfoEditText;
    private TextView mToolbarTitleText ,mDeadlineText, mTagHelpText;
    private TextView mTitleConstraintText, mInfoConstraintText, mTagConstraintText;
    private Spinner mCategorySelectSpinner;
    private TagsEditText mTagEditText;

    private View.OnClickListener mClickListner;
    private View.OnFocusChangeListener mFocusListner;
    private View.OnTouchListener mTouchListner;
    private DatePickerDialog.OnDateSetListener mDateLisner;

    private InputMethodManager mImManager;

    private ArrayAdapter mCategoryAdapter;

    private Intent mIntentForResult;

    private Calendar mDateData;

    private boolean mDeadlineChecker;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createparty);

        mPresenter = new CreatePartyPresenter(this);

        initView();
    }

    public void initView() {

        mContentLayout = (LinearLayout) findViewById(R.id.createparty_partycontent_layout);

        mPartyCancelBtn = (Button) findViewById(R.id.createparty_cancel_party_btn);
        mPartyRegisterBtn = (Button) findViewById(R.id.createparty_register_party_btn);
        mInfoConfirmBtn = (Button) findViewById(R.id.createparty_confirm_btn);

        mTitleEditText = (EditText) findViewById(R.id.createparty_title_edt);
        mMemberNumEditText = (EditText) findViewById(R.id.createparty_membernum_edt);
        mInfoEditText = (EditText) findViewById(R.id.createparty_information_edt);
        mTagEditText = (TagsEditText) findViewById(R.id.createparty_tag_edt);

        mToolbarTitleText = (TextView) findViewById(R.id.createparty_toolbar_title_txt);
        mDeadlineText = (TextView) findViewById(R.id.createparty_select_deadline_txt);
        mTitleConstraintText = (TextView) findViewById(R.id.createparty_title_constraint_txt);
        mInfoConstraintText = (TextView) findViewById(R.id.createparty_information_constraint_txt);
        mTagConstraintText = (TextView) findViewById(R.id.createparty_tag_constraint_txt);
        mTagHelpText = (TextView) findViewById(R.id.createparty_tag_help_txt);

        mCategorySelectSpinner = (Spinner) findViewById(R.id.createparty_select_category_spn);

        mCategoryAdapter = ArrayAdapter.createFromResource(CreatePartyActivity.this, R.array.group_category,
                R.layout.item_spinner);

        mImManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mInfoConfirmBtn.setVisibility(View.GONE);
        mTagHelpText.setVisibility(View.GONE);

        mToolbarTitleText.setFocusableInTouchMode(true);
        mCategorySelectSpinner.setAdapter(mCategoryAdapter);

        mTagEditText.setTagsWithSpacesEnabled(true);
        mTagEditText.setThreshold(1);

        mDeadlineChecker = false;

        mTitleEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 19) {
                    mTitleConstraintText.setText("20/20");
                    mTitleConstraintText.setTextColor(Color.RED);
                } else {
                    mTitleConstraintText.setText(String.valueOf(s.length())+"/20");
                    mTitleConstraintText.setTextColor(Color.parseColor("#44394d"));
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
                    mInfoConstraintText.setText(String.valueOf(s.length())+"/60");
                    mInfoConstraintText.setTextColor(Color.parseColor("#44394d"));
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
                    if(s.length() < beforeSize && tags.size() > 0)
                        tags.remove(tags.size()-1);
                    mTagConstraintText.setText(String.valueOf(tags.size())+"/4");
                    mTagConstraintText.setTextColor(Color.parseColor("#44394d"));
                    mTagEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mDeadlineChecker = false;

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
                        if (mPresenter.checkInputData(mTitleEditText,mCategorySelectSpinner,
                                mDeadlineText, mMemberNumEditText,mInfoEditText, mTagEditText)) {
                            mIntentForResult = mPresenter.putDataToIntent();
                            setResult(Activity.RESULT_OK, mIntentForResult);
                            finish();
                        }
                        else{
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
                    if(view.getId() == R.id.createparty_tag_edt) {
                        mTagHelpText.setVisibility(View.VISIBLE);
                    }
                } else {
                    mPartyCancelBtn.setVisibility(View.VISIBLE);
                    mInfoConfirmBtn.setVisibility(View.GONE);
                    if(view.getId() == R.id.createparty_tag_edt) {
                        mTagHelpText.setVisibility(View.GONE);
                    }
                }
            }
        };

        setFocusListner(mTitleEditText);
        setFocusListner(mMemberNumEditText);
        setFocusListner(mInfoEditText);
        setFocusListner(mTagEditText);
    }

    void hideVirtualKeyboard(View view) {
        mToolbarTitleText.requestFocus();
        mImManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    void showDatePickerDialog(){
        mDeadlineChecker = true;

        mDateData = mPresenter.getCalenderData();

        mDateLisner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String dateText = mPresenter.checkCorrectDeadline(year, month, day);
                mDeadlineText.setText(dateText);
                mDeadlineText.setTextColor(Color.parseColor("#44394d"));
            }
        };

        new DatePickerDialog(CreatePartyActivity.this,
                mDateLisner, mDateData.get(Calendar.YEAR), mDateData.get(Calendar.MONTH),
                mDateData.get(Calendar.DAY_OF_MONTH)).show();
    }

    void setTouchListner(View view) {
        view.setOnTouchListener(mTouchListner);
    }

    void setClickListner(View view) {
        view.setOnClickListener(mClickListner);
    }

    void setFocusListner(View view) {
        view.setOnFocusChangeListener(mFocusListner);
    }

    boolean getDeadlineChecker(){ return mDeadlineChecker; }
}
