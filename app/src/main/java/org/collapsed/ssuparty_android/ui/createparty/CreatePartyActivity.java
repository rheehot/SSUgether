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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreatePartyActivity extends AppCompatActivity{

    private final int EDIT_MAX_NUMBER = 15;

    private Button mBtnCancel, mBtnRegister, mBtnConfirm;
    private EditText mEdtTitle, mEdtMemberNum;
    private TextView mTxtDeadline, mTxtTitlebar;
    private Spinner mSpnCategory;
    private TextInputLayout mTxtlayoutTitle;
    private View.OnClickListener mClickListner;
    private View.OnFocusChangeListener mFocusListner;

    private InputMethodManager mImManager;

    private ArrayAdapter mCgAdapter;

    private int mYear, mMonth, mDay;

    Calendar mCalender;
    DatePickerDialog.OnDateSetListener mDateLisner;

    String mStrTitle, mStrMemberNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createparty);

        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);

        mEdtTitle = (EditText) findViewById(R.id.edt_group_title);
        mEdtMemberNum = (EditText) findViewById(R.id.edt_group_membernum);

        mTxtDeadline = (TextView) findViewById(R.id.txt_group_deadline);
        mTxtTitlebar = (TextView) findViewById(R.id.txt_group_titlebar);

        mTxtlayoutTitle = (TextInputLayout) findViewById(R.id.layout_edt_title);

        mSpnCategory = (Spinner) findViewById(R.id.spn_group_category);
        mCgAdapter = ArrayAdapter.createFromResource(CreatePartyActivity.this,R.array.group_category,
                android.R.layout.simple_spinner_dropdown_item);

        mCalender = new GregorianCalendar();
        mYear = mCalender.get(Calendar.YEAR);
        mMonth = mCalender.get(Calendar.MONTH);
        mDay = mCalender.get(Calendar.DAY_OF_MONTH);

        mImManager =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        initView();
    }

    public void initView(){

        mBtnConfirm.setVisibility(View.GONE);
        mTxtTitlebar.setFocusableInTouchMode(true);

        mSpnCategory.setAdapter(mCgAdapter);

        mTxtlayoutTitle.setCounterEnabled(true);
        mTxtlayoutTitle.setCounterMaxLength(EDIT_MAX_NUMBER);

        mEdtMemberNum.setOnFocusChangeListener(mFocusListner);

        mDateLisner = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                mYear = year;
                mMonth = month + 1;
                mDay = day;
                updateDeadline();
            }
        };

        mClickListner = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.btn_register:
                        mStrTitle = mEdtTitle.getText().toString();
                        mStrMemberNum = mEdtMemberNum.getText().toString();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("title", mStrTitle);
                        returnIntent.putExtra("memberNum", mStrMemberNum);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                        break;

                    case R.id.btn_confirm:
                        mTxtTitlebar.requestFocus();
                        mImManager.hideSoftInputFromWindow(mBtnConfirm.getWindowToken(), 0);
                        break;

                    case R.id.btn_cancel:
                        finish();
                        break;

                    case R.id.txt_group_deadline:
                        new DatePickerDialog(CreatePartyActivity.this,
                                mDateLisner, mYear, mMonth, mDay).show();;
                        break;
                }
            }
        };

        mBtnConfirm.setOnClickListener(mClickListner);
        mBtnCancel.setOnClickListener(mClickListner);
        mBtnRegister.setOnClickListener(mClickListner);
        mTxtDeadline.setOnClickListener(mClickListner);

        mFocusListner = new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean getFocus) {

                if(getFocus){
                    switch (view.getId()) {
                        case R.id.edt_group_title:
                            mTxtTitlebar.setText("제목");
                            break;
                        case R.id.edt_group_membernum:
                            mTxtTitlebar.setText("모집인원");
                            break;
                    }
                    mBtnRegister.setVisibility(View.GONE);
                    mBtnConfirm.setVisibility(View.VISIBLE);
                }
                else{
                    mTxtTitlebar.setText("새 모임");
                    mBtnRegister.setVisibility(View.VISIBLE);
                    mBtnConfirm.setVisibility(View.GONE);
                }
            }
        };

        mEdtTitle.setOnFocusChangeListener(mFocusListner);
        mEdtMemberNum.setOnFocusChangeListener(mFocusListner);

    }

    public void updateDeadline(){
        mTxtDeadline.setText(mYear+"."+mMonth+"."+mDay);
    }

}
