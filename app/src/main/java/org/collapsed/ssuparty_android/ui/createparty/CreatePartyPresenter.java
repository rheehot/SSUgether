package org.collapsed.ssuparty_android.ui.createparty;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
