package org.collapsed.ssuparty_android.ui.findparty;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

class FindPartyPresenter implements FindPartyContract.UserActionListener {
    private static final String TAG = FindPartyPresenter.class.getSimpleName();

    private View mDialogView;
    private FindPartyFragment mView;

    public FindPartyPresenter(@NonNull FindPartyFragment view) {
        this.mView = checkNotNull(view);
    }

    public void getPartyInfo(Context context) {
        mDialogView = (View) View.inflate(context,
                R.layout.dialog_createparty, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle("그룹 정보 입력");
        dlg.setView(mDialogView);
        dlg.setPositiveButton("생성",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        EditText dTitle = mDialogView.findViewById(R.id.dlgTitle);
                        EditText dMember = mDialogView.findViewById(R.id.dlgMember);
                        mView.newParty(dTitle.getText().toString(), dMember.getText().toString());
                        }
                    });
        dlg.setNegativeButton("취소",
                null);
        dlg.show();
    }
}
