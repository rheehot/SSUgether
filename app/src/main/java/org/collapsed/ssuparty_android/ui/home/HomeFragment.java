package org.collapsed.ssuparty_android.ui.home;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.databinding.HomeFragmentBinding;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.customview.CircleImageView;
import org.collapsed.ssuparty_android.ui.partydetail.PartyDetailActivity;
import org.collapsed.ssuparty_android.ui.unionsearchlist.UnionSearchListActivity;
import org.collapsed.ssuparty_android.ui.userprofiledetail.UserProfileDetailActivity;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private HomePresenter mPresenter;
    private HomeFragmentBinding mBinding;
    private boolean mSearchResultState = false;
    private boolean mExecutingAnimation = false;
    private float mPositionYToolbar = 0.0f;
    private InputMethodManager mIMM;
    private ProgressDialog mDialog;

    private ProfileSearchAdapter mProfileAdapter;
    private PartySearchAdapter mPartyAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HomePresenter(this);
        mIMM = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        mBinding.setView(this);
        mBinding.setPresenter(mPresenter);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mPositionYToolbar = mBinding.homeToolbar.getY();
        mBinding.homeSearchEdittext.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.performSearch(textView.getText());
                changeSearchResultState();
                return true;
            }
            return false;
        });

        mBinding.homeShowAllProfile.setOnClickListener(view -> {
            moveToAllResultWithProfile();
        });

        mBinding.homeShowAllMeetups.setOnClickListener(view -> {
            moveToAllResultWithParty();
        });

        mDialog = new ProgressDialog(getActivity());
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("검색 결과를 로딩중입니다..");
    }

    public void moveToAllResultWithProfile() {
        Intent intent = new Intent(getActivity(), UnionSearchListActivity.class);
        intent.putExtra("ListResultCategory", 0);
        intent.putExtra("Items", mProfileAdapter.getItems());
        startActivity(intent);
    }

    public void moveToAllResultWithParty() {
        Intent intent = new Intent(getActivity(), UnionSearchListActivity.class);
        intent.putExtra("ListResultCategory", 1);
        intent.putExtra("Items", mPartyAdapter.getItems());
        startActivity(intent);
    }

    @Override
    public void changeSearchResultState() {
        if (!mSearchResultState && !mExecutingAnimation) {
            mExecutingAnimation = true;
            animateToSearchResultState();
            mSearchResultState = !mSearchResultState;
        } else if (mSearchResultState && !mExecutingAnimation) {
            mExecutingAnimation = true;
            animateToDefaultState();
            mSearchResultState = !mSearchResultState;
        }
    }

    public void animateToDefaultState() {
        AnimatorSet fadein = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.fadein_anim);
        ObjectAnimator alignCenter = ObjectAnimator.ofFloat(mBinding.homeToolbar, "translationY", mPositionYToolbar);
        AnimatorSet bgToWhite = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.bg_to_white_anim);
        AnimatorSet bgToPrimaryColor = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.bg_to_primary_anim);

        fadein.setTarget(mBinding.homeLogo);
        alignCenter.setTarget(mBinding.homeToolbar);
        bgToWhite.setTarget(mBinding.homeBg);
        bgToPrimaryColor.setTarget(mBinding.homeBg);
        fadein.setStartDelay(300);
        alignCenter.setStartDelay(300);

        mBinding.homeSearchResultLayout.setVisibility(View.GONE);

        bgToWhite.start();
        bgToPrimaryColor.start();
        fadein.start();
        alignCenter.start();
        alignCenter.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mExecutingAnimation = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void animateToSearchResultState() {
        mIMM.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        AnimatorSet fadeout = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.fadeout_anim);
        AnimatorSet alignTop = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.searchbar_up_to_anim);
        AnimatorSet bgToWhite = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.bg_to_white_anim);
        AnimatorSet bgToPrimaryColor = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.bg_to_primary_anim);

        fadeout.setTarget(mBinding.homeLogo);
        alignTop.setTarget(mBinding.homeToolbar);
        bgToWhite.setTarget(mBinding.homeBg);
        bgToWhite.setStartDelay(600);
        bgToPrimaryColor.setTarget(mBinding.homeToolbar);
        bgToWhite.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mBinding.homeSearchResultLayout.setVisibility(View.VISIBLE);
                mExecutingAnimation = false;
//                mDialog.show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        fadeout.start();
        alignTop.start();
        bgToWhite.start();
        bgToPrimaryColor.start();
    }

    public void setupProfileList(ArrayList<UserInfoData> profiles) {
        ListView listView = mBinding.homeProfileList;
        mProfileAdapter = new ProfileSearchAdapter(getActivity(), profiles);
        listView.setAdapter(mProfileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), UserProfileDetailActivity.class);
                intent.putExtra("UserInfo", (UserInfoData) adapterView.getItemAtPosition(i));
                startActivity(intent);
            }
        });
    }

    public void setupPartyList(ArrayList<PartyData> parties) {
        ListView listView = mBinding.homePartyList;
        mPartyAdapter = new PartySearchAdapter(getActivity(), parties);
        listView.setAdapter(mPartyAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getActivity(), PartyDetailActivity.class);
            intent.putExtra("PartyData", (PartyData)adapterView.getItemAtPosition(i));
            startActivity(intent);
        });
    }

    private class ProfileSearchAdapter extends BaseAdapter {
        private final ArrayList<UserInfoData> mUserInfos;
        private LayoutInflater mInflater;
        private ViewHolder viewHolder;

        public ProfileSearchAdapter(Context context, ArrayList<UserInfoData> profiles) {
            this.mUserInfos = profiles;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mUserInfos.size();
        }

        @Override
        public UserInfoData getItem(int i) {
            return mUserInfos.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public ArrayList<UserInfoData> getItems() {
            return mUserInfos;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = convertView;

            if (view == null) {
                viewHolder = new ViewHolder();
                view = mInflater.inflate(R.layout.layout_userprofile_row, null);
                viewHolder.profileImage = view.findViewById(R.id.userprofile_image);
                viewHolder.profileNameText = view.findViewById(R.id.userprofile_name_txt);
                viewHolder.profileIntroduce = view.findViewById(R.id.userprofile_intro_txt);
                viewHolder.profileEmailText = view.findViewById(R.id.userprofile_email_txt);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            UserInfoData userData = mUserInfos.get(i);

            if (userData.getImgUrl() != null) {
                ImageUtil.loadUrlImage(viewHolder.profileImage, userData.getImgUrl());
            }
            viewHolder.profileNameText.setText(userData.getName());
            if (userData.getIntro() != null) {
                viewHolder.profileIntroduce.setText("Bio : " + userData.getIntro());
            }
            viewHolder.profileEmailText.setText("Email : " + userData.getEmail());

            return view;
        }

        class ViewHolder {
            CircleImageView profileImage = null;
            TextView profileNameText = null;
            TextView profileIntroduce = null;
            TextView profileEmailText = null;
        }
    }

    private class PartySearchAdapter extends BaseAdapter {
        private final ArrayList<PartyData> mParties;
        private LayoutInflater mInflater;
        private ViewHolder viewHolder;

        public PartySearchAdapter(Context context, ArrayList<PartyData> parties) {
            this.mParties = parties;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mParties.size();
        }

        @Override
        public PartyData getItem(int i) {
            return mParties.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public ArrayList<PartyData> getItems() {
            return mParties;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = convertView;

            if (view == null) {
                viewHolder = new ViewHolder();
                view = mInflater.inflate(R.layout.layout_party_row, null);
                viewHolder.partyTitleText = view.findViewById(R.id.party_row_title);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.partyTitleText.setText(mParties.get(i).getTitle());
            return view;
        }

        class ViewHolder {
            TextView partyTitleText = null;
        }
    }

    public boolean getSearchResultState() {
        return mSearchResultState;
    }
}
