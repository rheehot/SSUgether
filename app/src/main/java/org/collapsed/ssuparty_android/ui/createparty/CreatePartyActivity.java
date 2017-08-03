package org.collapsed.ssuparty_android.ui.createparty;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.NewPartyInfo;
import org.collapsed.ssuparty_android.ui.customview.TagsEditText;

import java.util.List;

public class CreatePartyActivity extends AppCompatActivity implements CreatePartyContract.View {

    private static final int TITLE_MAX_LENGTH = 15;
    private static final int INFORMATION_MAX_LENGTH = 60;

    private CreatePartyPresenter mPresenter;

    private NewPartyInfo mPartyInfoObeject;

    private RelativeLayout mContentLayout;

    private Button mPartyCancelBtn, mPartyRegisterBtn, mInfoConfirmBtn;
    private EditText mTitleEditText, mMemberNumEditText, mPartyInfoEditText;
    private TextView mToolbarTitleText ,mDeadlineText;
    private Spinner mCategorySelectSpinner;
    private TextInputLayout mInputTitleLayout, mInputInformationLayout;
    private TagsEditText mTagEditText;

    private View.OnClickListener mClickListner;
    private View.OnFocusChangeListener mFocusListner;
    private View.OnTouchListener mTouchListner;
    private DatePickerDialog.OnDateSetListener mDateLisner;

    private InputMethodManager mImManager;

    private ArrayAdapter mCategoryAdapter;

    private String mPartyTitle, mPartyMemberNum, mPartyCategory, mPartyInfo, mPartyDeadline;
    private List<String> mPartyTags;

    private int mYear, mMonth, mDay;
    private int[] mDateData;

    private boolean mDeadlineChecker;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createparty);

        mPresenter = new CreatePartyPresenter(this);

        initView();
    }

    public void initView() {

        mContentLayout = (RelativeLayout) findViewById(R.id.createparty_partycontent_layout);

        mPartyCancelBtn = (Button) findViewById(R.id.createparty_cancel_party_btn);
        mPartyRegisterBtn = (Button) findViewById(R.id.createparty_register_party_btn);
        mInfoConfirmBtn = (Button) findViewById(R.id.createparty_confirm_btn);

        mTitleEditText = (EditText) findViewById(R.id.createparty_input_title_edt);
        mMemberNumEditText = (EditText) findViewById(R.id.createparty_input_membernum_edt);
        mPartyInfoEditText = (EditText) findViewById(R.id.createparty_input_information_edt);
        mTagEditText = (TagsEditText) findViewById(R.id.createparty_tag_edt);

        mToolbarTitleText = (TextView) findViewById(R.id.createparty_toolbar_title_txt);
        mDeadlineText = (TextView) findViewById(R.id.createparty_select_deadline_txt);

        mInputTitleLayout = (TextInputLayout) findViewById(R.id.createparty_input_title_layout);
        mInputInformationLayout = (TextInputLayout) findViewById(R.id.createparty_input_information_layout);

        mCategorySelectSpinner = (Spinner) findViewById(R.id.createparty_select_category_spn);

        mCategoryAdapter = ArrayAdapter.createFromResource(CreatePartyActivity.this, R.array.group_category,
                android.R.layout.simple_spinner_dropdown_item);

        mImManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mInfoConfirmBtn.setVisibility(View.GONE);

        mToolbarTitleText.setFocusableInTouchMode(true);
        mCategorySelectSpinner.setAdapter(mCategoryAdapter);

        mInputTitleLayout.setCounterEnabled(true);
        mInputTitleLayout.setCounterMaxLength(TITLE_MAX_LENGTH);
        mInputInformationLayout.setCounterEnabled(true);
        mInputInformationLayout.setCounterMaxLength(INFORMATION_MAX_LENGTH);

        mTagEditText.setTagsWithSpacesEnabled(true);
        mTagEditText.setThreshold(1);

        mDeadlineChecker = false;

        mTouchListner = new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()){
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
                        /*
                        mTitle = mTitleEditText.getText().toString();
                        mMemberNum = mMemberNumEditText.getText().toString();
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();*/

                        if(checkInputData()){
                            mPartyInfoObeject = new NewPartyInfo(mPartyTitle, mPartyMemberNum,
                                    mPartyCategory, mPartyDeadline, mPartyInfo, mPartyTags);
                            Log.d("data",mPartyInfoObeject.getTitle());
                            Log.d("data",mPartyInfoObeject.getCategory());
                            Log.d("data",mPartyInfoObeject.getDeadline());
                            Log.d("data",mPartyInfoObeject.getInformation());
                            Log.d("data",mPartyInfoObeject.getMemberNum());
                            Log.d("data",""+mPartyInfoObeject.getTags().size());
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
                    switch (view.getId()) {
                        case R.id.createparty_input_title_edt:
                            mToolbarTitleText.setText("모임명");
                            break;
                        case R.id.createparty_input_membernum_edt:
                            mToolbarTitleText.setText("모집인원");
                            break;
                        case R.id.createparty_input_information_edt:
                            mToolbarTitleText.setText("모임소개");
                            break;
                        case R.id.createparty_tag_edt:
                            mToolbarTitleText.setText("태그추가");
                            break;
                        case R.id.createparty_select_category_spn:
                            mToolbarTitleText.setText("카테고리 선택");
                            break;
                        default:
                            break;
                    }
                    mPartyRegisterBtn.setVisibility(View.GONE);
                    mPartyCancelBtn.setVisibility(View.GONE);
                    mInfoConfirmBtn.setVisibility(View.VISIBLE);
                } else {
                    mToolbarTitleText.setText("새 모임");
                    mPartyRegisterBtn.setVisibility(View.VISIBLE);
                    mPartyCancelBtn.setVisibility(View.VISIBLE);
                    mInfoConfirmBtn.setVisibility(View.GONE);
                }
            }
        };

        setFocusListner(mTitleEditText);
        setFocusListner(mMemberNumEditText);
        setFocusListner(mPartyInfoEditText);
        setFocusListner(mTagEditText);
    }

    void hideVirtualKeyboard(View view){
        mToolbarTitleText.requestFocus();
        mImManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    void showDatePickerDialog(){
        mDeadlineChecker = true;

        mDateData = mPresenter.getCalenderData();

        mYear = mDateData[0];
        mMonth = mDateData[1];
        mDay = mDateData[2];

        mDateLisner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month + 1;
                mDay = day;
                mDeadlineText.setText(mYear + "." + mMonth + "." + mDay);
            }
        };

        new DatePickerDialog(CreatePartyActivity.this,
                mDateLisner, mYear, mMonth, mDay).show();
    }

   boolean checkInputData(){
        boolean testValue = false;
        if(checkTextLength(mTitleEditText)) {
            mPartyTitle = mTitleEditText.getText().toString();
        }
        else{
            Toast.makeText(this,"모임명이 누락되었습니다!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(mCategorySelectSpinner.getSelectedItem().toString().equals("카테고리선택")){
            Toast.makeText(this,"카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            mPartyCategory = mCategorySelectSpinner.getSelectedItem().toString();
        }

        if(mDeadlineChecker){
            mPartyDeadline = mDeadlineText.getText().toString();
        }
        else{
            Toast.makeText(this,"마감 기한을 선택해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(checkTextLength(mMemberNumEditText)) {
            mPartyMemberNum = mMemberNumEditText.getText().toString();
        }
        else{
            Toast.makeText(this,"모집인원이 누락되었습니다!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(checkTextLength(mPartyInfoEditText)) {
            mPartyInfo = mPartyInfoEditText.getText().toString();
        }
        else{
            Toast.makeText(this,"모임 소개가 누락되었습니다!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(checkTextLength(mTitleEditText)) {
            mPartyTags = mTagEditText.getTags();
        }

        return true;
    }

    boolean checkTextLength(EditText view){
        if(view.getText().toString().length() >= 1){
            return true;
        }
        else
            return false;
    }

    void setTouchListner(View view){
        view.setOnTouchListener(mTouchListner);
    }

    void setClickListner(View view){
        view.setOnClickListener(mClickListner);
    }

    void setFocusListner(View view){
        view.setOnFocusChangeListener(mFocusListner);
    }
}
