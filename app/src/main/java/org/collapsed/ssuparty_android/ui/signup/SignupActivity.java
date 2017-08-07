package org.collapsed.ssuparty_android.ui.signup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.collapsed.ssuparty_android.R;

public class SignupActivity extends AppCompatActivity {

    private final static int SIGNUP_PAGER_COUNT = 2;

    private SignupPresenter mPresenter;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mPresenter = new SignupPresenter(this);

        mViewPager = (ViewPager) findViewById(R.id.signup_pager);
        mPagerAdapter = new SignupPagerAdapter(getSupportFragmentManager(), mPresenter);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private static class SignupPagerAdapter extends FragmentStatePagerAdapter {
        private SignupPresenter presenter;

        public SignupPagerAdapter(FragmentManager fm, SignupPresenter presenter) {
            super(fm);
            this.presenter = presenter;
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return Signup1Fragment.newInstance(presenter);
            } else if (position == 1) {
                return Signup2Fragment.newInstance(presenter);
            }

            throw new RuntimeException("Invalid Signup Pager postion");
        }

        @Override
        public int getCount() {
            return SIGNUP_PAGER_COUNT;
        }
    }
}
