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

public class CreatePartyPresenter implements CreatePartyContract.CheckData, CreatePartyContract.ReturnData {

    private static final String TITLE_KEY = "title";
    private static final String CATEGORY_KEY = "category";
    private static final String DEADLINE_KEY = "deadline";
    private static final String MEMBER_KEY = "memberNum";
    private static final String INFO_KEY = "info";
    private static final String TAG_KEY = "tag";

    private CreatePartyActivity mView;

    private Calendar mCalender;

    private String mPartyTitle, mPartyMemberNum, mPartyCategory, mPartyInfo, mPartyDeadline;
    private List<String> mPartyTags;

    private boolean mDeadlineCheck;

    public CreatePartyPresenter(@NonNull CreatePartyActivity view) {
        this.mView = checkNotNull(view);
    }

    public Calendar getCalenderData() {
        mCalender = new GregorianCalendar();

        return mCalender;
    }

    public boolean checkInputData(EditText title, Spinner category, TextView deadline,
                                  EditText member, EditText info, TagsEditText tag) {
        boolean checkValue = true;
        if (checkTextLength(title)) {
            mPartyTitle = title.getText().toString();
        } else {
            checkValue = false;
        }

        if (!category.getSelectedItem().toString().equals("카테고리선택")) {
            mPartyCategory = category.getSelectedItem().toString();
        } else {
            checkValue = false;
        }

        if (mDeadlineCheck) {
            mPartyDeadline = deadline.getText().toString();
        } else {
            checkValue = false;
        }

        if (checkTextLength(member)) {
            if(checkOverNumber(member.getText().toString())){
                mPartyMemberNum = member.getText().toString();
            }
            else {
                mView.setMemberNumTextByException("모집 인원수 제한을 초과했어요!");
                checkValue = false;
            }
        } else {
            checkValue = false;
        }

        if (checkTextLength(info)) {
            mPartyInfo = info.getText().toString();
        } else {
            checkValue = false;
        }

        if (tag.getTags().size() > 0) {
            mPartyTags = tag.getTags();
        }

        return checkValue;
    }

    public Intent putDataToIntent() {
        Intent intent = new Intent();
        intent.putExtra(TITLE_KEY, mPartyTitle);
        intent.putExtra(CATEGORY_KEY, mPartyCategory);
        intent.putExtra(DEADLINE_KEY, mPartyDeadline);
        intent.putExtra(INFO_KEY, mPartyInfo);
        intent.putExtra(MEMBER_KEY, mPartyMemberNum);
        intent.putStringArrayListExtra(TAG_KEY, (ArrayList<String>) mPartyTags);

        return intent;
    }

    public boolean checkTextLength(EditText view) {
        if (view.getText().toString().length() > 0) {
            return true;
        } else
            return false;
    }

    public boolean checkOverNumber(String numberText){
        if(Integer.parseInt(numberText) <= 100) {
            return true;
        }
        else {
            return false;
        }
    }

    public String checkCorrectDeadline(int year, int month, int day) {
        Calendar dateData = getCalenderData();

        Date curDate = new Date(dateData.get(Calendar.YEAR), dateData.get(Calendar.MONTH),
                dateData.get(Calendar.DAY_OF_MONTH));
        Date selectDate = new Date(year, month, day);

        if (curDate.getTime() > selectDate.getTime()) {
            mDeadlineCheck = false;
            return "마감날짜를 다시 선택해주세요!";
        } else {
            mDeadlineCheck = true;
            month = month + 1;
            return year + "." + month + "." + day;
        }
    }

}
