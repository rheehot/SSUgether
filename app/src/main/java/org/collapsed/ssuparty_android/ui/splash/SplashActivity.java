package org.collapsed.ssuparty_android.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.account.AccountActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashPresenter mPresenter;

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter = new SplashPresenter(this);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = mPresenter.getIntentForNextActivity();
                startActivity(intent);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 5000);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
