package org.collapsed.ssuparty_android.ui.userprofiledetail;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.databinding.UserProfileDetailActivityBinding;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import me.gujun.android.taggroup.TagGroup;

public class UserProfileDetailActivity extends AppCompatActivity {
    private UserProfileDetailActivityBinding mBinding;
    private UserInfoData mUserInfo;
    private TagGroup mTagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.user_profile_detail_activity);

        mUserInfo = (UserInfoData) getIntent().getSerializableExtra("UserInfo");

        initView();
    }

    private void initView() {
        ImageUtil.loadUrlImage(mBinding.userdetailprofileUserImage, mUserInfo.getImgUrl());

        mBinding.userdetailprofileTitle.setText(mUserInfo.getName() + "님의 프로필");
        mBinding.userdetailprofileNameTxt.setText(mUserInfo.getName());
        mBinding.userdetailprofileMajorTxt.setText(mUserInfo.getMajor());
        mBinding.userdetailprofileGradeTxt.setText(mUserInfo.getGrade());
        mBinding.userdetailprofileIntroTxt.setText(mUserInfo.getIntro());
        mTagLayout = mBinding.userdetailprofileTags;
        if (mUserInfo.getTags() != null) {
            mTagLayout.setTags(mUserInfo.getTags());
        }

        mBinding.userdetailprofileCancelBtn.setOnClickListener(view -> finish());
    }
}
