package org.collapsed.ssuparty_android.ui.createparty;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.collapsed.ssuparty_android.ui.customview.TagsEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreatePartyPresenter implements CreatePartyContract.UserActionListener{

    private CreatePartyActivity mView;

    private Calendar mCalender;

    private String mPartyTitle, mPartyMemberNum, mPartyCategory, mPartyInfo, mPartyDeadline;
    private List<String> mPartyTags;

    public CreatePartyPresenter(@NonNull CreatePartyActivity view){
        this.mView = checkNotNull(view);
    }

    public Calendar getCalenderData(){
        mCalender = new GregorianCalendar();

        return mCalender;
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

    String checkCorrectDeadline(int year, int month, int day){
        Calendar dateData = getCalenderData();

        Date curDate = new Date(dateData.get(Calendar.YEAR), dateData.get(Calendar.MONTH),
                dateData.get(Calendar.DAY_OF_MONTH));
        Date selectDate = new Date(year, month, day);

        if(curDate.getTime() > selectDate.getTime()){
            return "마감날짜를 다시 선택해주세요!";
        }
        else {
            month = month + 1;
            return year + "." + month + "." + day;
        }
    }

}
