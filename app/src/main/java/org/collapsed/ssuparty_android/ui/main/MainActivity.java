package org.collapsed.ssuparty_android.ui.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.createparty.CreatePartyActivity;
import org.collapsed.ssuparty_android.ui.partydetail.PartyDetailActivity;
import org.collapsed.ssuparty_android.ui.partylist.PartyListFragment;
import org.collapsed.ssuparty_android.ui.customview.CustomTypefaceSpan;
import org.collapsed.ssuparty_android.ui.customview.MainViewPager;
import org.collapsed.ssuparty_android.helper.BottomNavigationViewHelper;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.home.HomeFragment;
import org.collapsed.ssuparty_android.ui.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View, BaseFragment.OnFragmentInteractionListener, ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int PAGE_COUNT = 4;
    private static final int FONT_BOLD = 1;
    private static final int FONT_REGULAR = 0;
    private static final int INDEX_HOME = 0;
    private static final int INDEX_MY_PARTY = 1;
    private static final int INDEX_ALL_PARTY = 2;
    private static final int INDEX_PROFILE = 3;

    @BindView(R.id.main_pager)
    MainViewPager mViewPager;
    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    private MainPresenter mPresenter;

    private static HomeFragment mHomeView;
    private static PartyListFragment mAllPartyView, mMyPartyView;
    private static ProfileFragment mProfileView;
    private BottomNaviPagerAdapter mBottomNaviAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);

        initView();
    }

    private void initView() {
        mBottomNaviAdapter = new BottomNaviPagerAdapter(getSupportFragmentManager(), PAGE_COUNT);
        mViewPager.setAdapter(mBottomNaviAdapter);
        mViewPager.setOffscreenPageLimit(PAGE_COUNT);
        mViewPager.setPagingEnabled(false);
        mViewPager.addOnPageChangeListener(this);

        applyFontToBottomNavigationView(mBottomNavigationView, FONT_BOLD);
        applyFontToBottomNavigationViewItem(mBottomNavigationView, FONT_BOLD, INDEX_HOME);

        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bn_home:
                        mViewPager.setCurrentItem(INDEX_HOME, false);
                        inflateViewInPage(INDEX_HOME);
                        return true;

                    case R.id.bn_myparty:
                        mViewPager.setCurrentItem(INDEX_MY_PARTY, false);
                        inflateViewInPage(INDEX_MY_PARTY);
                        return true;

                    case R.id.bn_allparty:
                        mViewPager.setCurrentItem(INDEX_ALL_PARTY, false);
                        inflateViewInPage(INDEX_ALL_PARTY);
                        return true;

                    case R.id.bn_profile:
                        mViewPager.setCurrentItem(INDEX_PROFILE, false);
                        inflateViewInPage(INDEX_PROFILE);
                        return true;
                }
                return false;
            }
        });
    }

    private void inflateViewInPage(int index) {
        switch (index) {
            case INDEX_HOME:
                break;

            case INDEX_MY_PARTY:
                mMyPartyView.inflateView(index);
                break;

            case INDEX_ALL_PARTY:
                mAllPartyView.inflateView(index);
                break;

            case INDEX_PROFILE:
                break;

            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        MenuItem menuitem = mBottomNavigationView.getMenu().getItem(position);
        menuitem.setChecked(true);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void applyFontToMenuItem(MenuItem mi, int option) {
        Typeface font = null;
        switch (option) {
            case FONT_REGULAR:
                font = Typeface.createFromAsset(getAssets(), getString(R.string.naum_square_regular));
                break;

            case FONT_BOLD:
                font = Typeface.createFromAsset(getAssets(), getString(R.string.naum_square_bold));
                break;
        }

        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public void applyFontToBottomNavigationViewItem(BottomNavigationView mBottomNavigationView, int option, int index) {
        Menu m = mBottomNavigationView.getMenu();
        MenuItem mi = m.getItem(index);
        SubMenu subMenu = mi.getSubMenu();

        if (subMenu != null && subMenu.size() > 0) {
            for (int j = 0; j < subMenu.size(); ++j) {
                MenuItem subMenuItem = subMenu.getItem(j);
                applyFontToMenuItem(subMenuItem, option);
            }
        }
        applyFontToMenuItem(mi, option);
    }

    public void applyFontToBottomNavigationView(BottomNavigationView mBottomNavigationView, int option) {
        for (int i = 0; i < PAGE_COUNT; ++i) {
            applyFontToBottomNavigationViewItem(mBottomNavigationView, option, i);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private static class BottomNaviPagerAdapter extends FragmentStatePagerAdapter {

        private int mTabCount;

        private BottomNaviPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.mTabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case INDEX_HOME:
                    mHomeView = HomeFragment.newInstance();
                    return mHomeView;

                case INDEX_MY_PARTY:
                    mMyPartyView = PartyListFragment.newInstance();
                    return mMyPartyView;


                case INDEX_ALL_PARTY:
                    mAllPartyView = PartyListFragment.newInstance();
                    return mAllPartyView;

                case INDEX_PROFILE:
                    mProfileView = ProfileFragment.newInstance();
                    return mProfileView;

                default:
                    return null;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return mTabCount;
        }
    }

    public HomeFragment getHomeFragment() {
        return mHomeView;
    }

    public PartyListFragment getMyPartyFragment() {
        return mMyPartyView;
    }

    public PartyListFragment getAllPartyFragment() {
        return mAllPartyView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent partyIndent) {
        super.onActivityResult(requestCode, resultCode, partyIndent);

        if (requestCode == CreatePartyActivity.CREATE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == CreatePartyActivity.RESULT_OK) {
                getAllPartyFragment().setNewPartyInfo(partyIndent);
            }
        } else if (requestCode == PartyDetailActivity.PARTY_DETAIL_ACTIVITY_REQUEST_CODE) {
            getAllPartyFragment().refreshPartyItems();
        }
    }

    @Override
    public void onBackPressed() {
        boolean checkState = mHomeView.getSearchResultState();

        if (checkState) {
            mHomeView.changeSearchResultState();
        } else {
            super.onBackPressed();
        }
    }
}
