package org.collapsed.ssuparty_android.ui.createparty;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.collapsed.ssuparty_android.ui.customview.TagsEditText;

public interface CreatePartyContract {
    interface View {
        void initView();

        void hideVirtualKeyboard(android.view.View view);

        void showDatePickerDialog();

        void setVisibility();

        void setMemNumException(String errorText);

        void setTouchListner(android.view.View view);

        void setClickListner(android.view.View view);

        void setFocusListner(android.view.View view);
    }

    interface Presenter {
        String checkCorrectDeadline(int year, int month, int day);


    }
}
