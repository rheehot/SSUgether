package org.collapsed.ssuparty_android.ui.createparty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.collapsed.ssuparty_android.ui.customview.TagsEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreatePartyPresenter implements CreatePartyContract.UserActionListener{

    private CreatePartyActivity mView;

    private int[] mDateData;

    private Calendar mCalender;

    private String mPartyTitle, mPartyMemberNum, mPartyCategory, mPartyInfo, mPartyDeadline;
    private List<String> mPartyTags;

    public CreatePartyPresenter(@NonNull CreatePartyActivity view){
        this.mView = checkNotNull(view);
    }

    public int[] getCalenderData(){
        mCalender = new GregorianCalendar();

        mDateData = new int[3];

        mDateData[0] = mCalender.get(Calendar.YEAR);
        mDateData[1] = mCalender.get(Calendar.MONTH);
        mDateData[2] = mCalender.get(Calendar.DAY_OF_MONTH);

        return mDateData;
    }

    boolean checkInputData(EditText title, Spinner category, TextView deadline,
    EditText member,EditText info, TagsEditText tag) {
        boolean testValue = false;
        if (checkTextLength(title)) {
            mPartyTitle = title.getText().toString();
        } else {
            return false;
        }

        if (category.getSelectedItem().toString().equals("카테고리선택")) {
            return false;
        } else {
            mPartyCategory = category.getSelectedItem().toString();
        }

        if (mView.getDeadlineChecker()) {
            mPartyDeadline = deadline.getText().toString();
        } else {
            return false;
        }

        if (checkTextLength(member)) {
            mPartyMemberNum = member.getText().toString();
        } else {
            return false;
        }

        if (checkTextLength(info)) {
            mPartyInfo = info.getText().toString();
        } else {
            return false;
        }

        if (tag.getTags().size()>1) {
            mPartyTags = tag.getTags();
        }

        return true;
    }

    Intent putDataToIntent(){
        Intent intent = new Intent();
        intent.putExtra("title",mPartyTitle);
        intent.putExtra("category",mPartyCategory);
        intent.putExtra("deadline",mPartyDeadline);
        intent.putExtra("info",mPartyInfo);
        intent.putExtra("memberNum",mPartyMemberNum);
        intent.putStringArrayListExtra("tag",(ArrayList<String>)mPartyTags);

        return intent;
    }

    boolean checkTextLength(EditText view) {
        if (view.getText().toString().length() >= 1) {
            return true;
        } else
            return false;
    }

}
