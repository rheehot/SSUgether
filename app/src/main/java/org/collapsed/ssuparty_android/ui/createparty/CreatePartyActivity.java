package org.collapsed.ssuparty_android.ui.createparty;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;

public class CreatePartyActivity extends AppCompatActivity implements CreatePartyContract.View{

    private final int EDIT_MAX_NUMBER = 15;

    private CreatePartyPresenter mPresenter;

    private Button mCancelParty, mRegisterParty, mConfirmInfo;
    private EditText mInputTitle, mInputMemberNum;
    private TextView mTxtDeadline, mToolbarTitle;
    private Spinner mSelectCategory;
    private TextInputLayout mInputTitleLayout;

    private View.OnClickListener mClickListner;
    private View.OnFocusChangeListener mFocusListner;
    private DatePickerDialog.OnDateSetListener mDateLisner;

    private InputMethodManager mImManager;

    private ArrayAdapter mCategoryAdapter;

    private String mStrTitle, mStrMemberNum;
    private int mYear, mMonth, mDay;
    private int [] mDateData;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createparty);

        mPresenter = new CreatePartyPresenter(this);

        initView();
    }

    public void initView(){

        mCancelParty = (Button) findViewById(R.id.createparty_cancel_party_btn);
        mRegisterParty = (Button) findViewById(R.id.createparty_register_party_btn);
        mConfirmInfo = (Button) findViewById(R.id.createparty_confirm_btn);

        mInputTitle = (EditText) findViewById(R.id.createparty_input_title_edt);
        mInputMemberNum = (EditText) findViewById(R.id.creatparty_input_membernum_edt);

        mTxtDeadline = (TextView) findViewById(R.id.createparty_select_deadline_txt);
        mToolbarTitle = (TextView) findViewById(R.id.createparty_toolbar_title_txt);

        mInputTitleLayout = (TextInputLayout) findViewById(R.id.createparty_input_title_layout);

        mSelectCategory = (Spinner) findViewById(R.id.createparty_select_category_spn);

        mCategoryAdapter = ArrayAdapter.createFromResource(CreatePartyActivity.this,R.array.group_category,
                android.R.layout.simple_spinner_dropdown_item);

        mImManager =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mConfirmInfo.setVisibility(View.GONE);
        mToolbarTitle.setFocusableInTouchMode(true);
        mSelectCategory.setAdapter(mCategoryAdapter);
        mInputTitleLayout.setCounterEnabled(true);
        mInputTitleLayout.setCounterMaxLength(EDIT_MAX_NUMBER);
        mInputMemberNum.setOnFocusChangeListener(mFocusListner);

        mDateData = mPresenter.getCalenderData();

        mYear = mDateData[0];
        mMonth = mDateData[1];
        mDay = mDateData[2];

        mDateLisner = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;
                mTxtDeadline.setText(mYear+"."+mMonth+"."+mDay);
            }
        };

        mClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.createparty_register_party_btn:
                        mStrTitle = mInputTitle.getText().toString();
                        mStrMemberNum = mInputMemberNum.getText().toString();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("title", mStrTitle);
                        returnIntent.putExtra("memberNum", mStrMemberNum);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                        break;

                    case R.id.createparty_confirm_btn:
                        mToolbarTitle.requestFocus();
                        mImManager.hideSoftInputFromWindow(mConfirmInfo.getWindowToken(), 0);
                        break;

                    case R.id.createparty_cancel_party_btn:
                        finish();
                        break;

                    case R.id.createparty_select_deadline_txt:
                        new DatePickerDialog(CreatePartyActivity.this,
                                mDateLisner, mYear, mMonth, mDay).show();;
                        break;
                }
            }
        };

        mConfirmInfo.setOnClickListener(mClickListner);
        mCancelParty.setOnClickListener(mClickListner);
        mRegisterParty.setOnClickListener(mClickListner);
        mTxtDeadline.setOnClickListener(mClickListner);

        mFocusListner = new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean getFocus) {

                if(getFocus){
                    switch (view.getId()) {
                        case R.id.createparty_input_title_edt:
                            mToolbarTitle.setText("제목");
                            break;
                        case R.id.creatparty_input_membernum_edt:
                            mToolbarTitle.setText("모집인원");
                            break;
                    }
                    mRegisterParty.setVisibility(View.GONE);
                    mConfirmInfo.setVisibility(View.VISIBLE);
                }
                else{
                    mToolbarTitle.setText("새 모임");
                    mRegisterParty.setVisibility(View.VISIBLE);
                    mConfirmInfo.setVisibility(View.GONE);
                }
            }
        };

        mInputTitle.setOnFocusChangeListener(mFocusListner);
        mInputMemberNum.setOnFocusChangeListener(mFocusListner);
    }
}
