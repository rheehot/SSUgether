package org.collapsed.ssuparty_android.ui.customview;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialog extends Dialog {

    private static final int NEGATIVE_MODE = 1;
    private static final int POSITIVE_MODE = 2;

    public CustomDialog(Context context) {
        super(context);
    }

    @BindView(R.id.intro_dialog_done_btn)
    Button mDoneButton;
    @BindView(R.id.intro_dialog_cancel_btn)
    Button mCancelButton;
    @BindView(R.id.intro_dialog_title_txt)
    TextView mTitleText;
    @BindView(R.id.intro_dialog_content_edit)
    EditText mContentEditText;

    private String mContentText;
    private int mModeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_profile);
        ButterKnife.bind(this);

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
    }

    public String getText() {
        return mContentText;
    }

    public int getMode() {return mModeNumber;}

}
