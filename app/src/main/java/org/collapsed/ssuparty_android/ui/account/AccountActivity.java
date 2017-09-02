package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.main.MainActivity;
import org.collapsed.ssuparty_android.ui.signup.SignupActivity;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AccountActivity extends AppCompatActivity implements AccountContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.account_facebook_login_btn)
    LoginButton mLoginButton;

    private AccountPresenter mPresenter;
    private CallbackManager mCallbackManager;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        mPresenter = new AccountPresenter(this);
        mCallbackManager = CallbackManager.Factory.create();
        mPresenter.setFacebookLoginCallback(mLoginButton, mCallbackManager);
        mProgress = (ProgressBar) findViewById(R.id.account_progress_circle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showFailedFBLogin() {
        Toast.makeText(this, "페이스북 로그인에 실패하였습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCanceledFBLogin() {
        Toast.makeText(this, "페이스북 로그인이 완료되지 않았습니다.", Toast.LENGTH_LONG).show();
    }

}
