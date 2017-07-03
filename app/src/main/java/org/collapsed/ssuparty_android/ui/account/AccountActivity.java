package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.main.MainActivity;

public class AccountActivity extends AppCompatActivity implements AccountContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AccountPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mPresenter = new AccountPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mPresenter.handleActivityResultWithCurrentSession(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.removeSessionCallback();
    }

    @Override
    public void redirectSignupActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
