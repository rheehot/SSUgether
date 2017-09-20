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

public class CreatePartyPresenter implements CreatePartyContract.Presenter {

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
