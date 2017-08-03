package org.collapsed.ssuparty_android;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kakao.auth.TokenAlarmReceiver;

public class TokenExpiredChecker extends TokenAlarmReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        Toast.makeText(context, "Token expired!", Toast.LENGTH_LONG).show();
    }
}
