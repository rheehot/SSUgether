package org.collapsed.ssuparty_android.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.profile_user_image_rouded_img)
    RoundedImageView mUserPhotoImageView;
    @BindView(R.id.profile_nickname_txt)
    TextView mNicknameText;
    @BindView(R.id.profile_major_txt)
    TextView mMajorText;
    @BindView(R.id.profile_grade_txt)
    TextView mGradeText;
    @BindView(R.id.profile_info_progressbar)
    ProgressBar mInfoProgressbar;

    private Unbinder mUnbinder;
    private Context mContext;
    private ProfileFragment mProfileFragment = this;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mUnbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        initView();
    }

    public void initView(){
        mUserPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON)
                        .setActivityTitle("편집")
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .setAspectRatio(1,1)
                        .setMinCropWindowSize(350, 350)
                        .start(mContext, mProfileFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
