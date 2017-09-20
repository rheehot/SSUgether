package org.collapsed.ssuparty_android.ui.customview;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroDialog extends Dialog {

    private static final int NEGATIVE_MODE = 1;
    private static final int POSITIVE_MODE = 2;
    private String mPreviousIntroText;

    public IntroDialog(Context context, String introText) {
        super(context);
        mPreviousIntroText = introText;
    }

    @BindView(R.id.intro_dialog_confirm_btn)
    Button mDoneButton;
    @BindView(R.id.intro_dialog_cancel_btn)
    Button mCancelButton;
    @BindView(R.id.intro_dialog_title_txt)
    TextView mTitleText;
    @BindView(R.id.intro_dialog_content_edit)
    EditText mContentEditText;
    @BindView(R.id.intro_dialog_constraint_txt)
    TextView mDialogConstraintText;

    private String mContentText;
    private int mModeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_profile);
        ButterKnife.bind(this);

        initView();


    }

    private void initView() {
        if(mPreviousIntroText != null) {
            mContentEditText.setText(mPreviousIntroText);
            mContentEditText.setSelection(mContentEditText.length());
        }

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContentText = mContentEditText.getText().toString();
                mModeNumber = POSITIVE_MODE;
                dismiss();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModeNumber = NEGATIVE_MODE;
                dismiss();
            }
        });

        mContentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence inputedText, int start, int before, int count) {
                if (inputedText.length() > 99) {
                    mDialogConstraintText.setText("100/100");
                    mDialogConstraintText.setTextColor(Color.RED);
                } else {
                    mDialogConstraintText.setText(String.valueOf(inputedText.length()) + "/100");
                    mDialogConstraintText.setTextColor(Color.parseColor("#828282"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });    }


    public String getText() {
        return mContentText;
    }

    public int getMode() {return mModeNumber;}

}
