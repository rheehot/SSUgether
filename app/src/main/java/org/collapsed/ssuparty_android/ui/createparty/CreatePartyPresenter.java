package org.collapsed.ssuparty_android.ui.createparty;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreatePartyPresenter implements CreatePartyContract.UserActionListener{

    private CreatePartyActivity mView;

    private int[] mDateData;

    private Calendar mCalender;

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

}
