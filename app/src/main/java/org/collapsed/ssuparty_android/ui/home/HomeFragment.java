package org.collapsed.ssuparty_android.ui.home;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.databinding.HomeFragmentBinding;
import org.collapsed.ssuparty_android.ui.BaseFragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private HomePresenter mPresenter;
    private HomeFragmentBinding mBinding;
    private boolean mSearchResultState = false;
    private boolean mExecutingAnimation = false;
    private float mPositionYToolbar = 0.0f;
    private InputMethodManager mIMM;

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

    public void showAllProfiles() {
        Toast.makeText(getActivity(), "dfdfdf", Toast.LENGTH_SHORT).show();
    }
}
