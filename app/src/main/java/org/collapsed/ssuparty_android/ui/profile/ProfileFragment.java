package org.collapsed.ssuparty_android.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.speccategory.SpecCategoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment  {

    @BindView(R.id.profile_user_image)
    RoundedImageView mUserPhotoImageView;
    @BindView(R.id.profile_nickname_txt)
    TextView mNicknameText;
    @BindView(R.id.profile_major_txt)
    TextView mMajorText;
    @BindView(R.id.profile_grade_txt)
    TextView mGradeText;
    @BindView(R.id.profile_info_progressbar)
    ProgressBar mInfoProgressbar;
    @BindView(R.id.profile_spec_btn)
    FloatingActionButton mSpecButton;

    private Unbinder mUnbinder;
    private Context mContext;
    private Uri imageUri;

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
                Intent intent = CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                        .setActivityTitle("편집")
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .setAspectRatio(1,1)
                        .getIntent(mContext);

                startActivityForResult(intent, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
            }
        });

        mSpecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext ,SpecCategoryActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("crop", "result ok");
                Log.d("crop", "requset code : " + requestCode);
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                mUserPhotoImageView.setImageURI(result.getUri());
                Toast.makeText(getActivity(), "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
