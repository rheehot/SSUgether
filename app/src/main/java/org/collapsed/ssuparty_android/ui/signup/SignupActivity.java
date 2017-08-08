package org.collapsed.ssuparty_android.ui.signup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.LinearLayout;

import org.collapsed.ssuparty_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    private final static int SIGNUP_PAGER_COUNT = 2;

    @BindView(R.id.signup_complete_layout)
    LinearLayout mCompleteLayout;

    private SignupPresenter mPresenter;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mPresenter = new SignupPresenter(this);

        mViewPager = (ViewPager) findViewById(R.id.signup_pager);
        mPagerAdapter = new SignupPagerAdapter(getSupportFragmentManager(), mPresenter);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1) {
                    mCompleteLayout.setVisibility(View.VISIBLE);
                } else {
                    mCompleteLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
