package org.collapsed.ssuparty_android.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.customview.IntroDialog;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.gujun.android.taggroup.TagGroup;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment {

    private static final int DIALOG_POSITIVE_MODE = 2;

    private static final Uri PROFILE_DEFAULT_IMAGE
            = Uri.parse("android.resource://org.collapsed.ssuparty_android/drawable/camera");


    @BindView(R.id.profile_main_layout)
    LinearLayout mMainLayout;
    @BindView(R.id.profile_user_image)
    ImageView mProfileImageView;
    @BindView(R.id.profile_nickname_txt)
    TextView mNicknameText;
    @BindView(R.id.profile_major_txt)
    TextView mMajorText;
    @BindView(R.id.profile_grade_txt)
    TextView mGradeText;

    @BindView(R.id.profile_intro_write_btn)
    ImageButton mWriteIntroButton;
    @BindView(R.id.profile_intro_content_txt)
    TextView mIntroContentText;
    @BindView(R.id.profile_tag_write_btn)
    ImageButton mWriteTagButton;
    @BindView(R.id.profile_tag_layout)
    TagGroup mTagLayout;

    private Unbinder mUnbinder;
    private Context mContext;
    private Uri imageUri;
    private View.OnClickListener mClickListener;
    private View.OnTouchListener mTouchListener;

    private ProfilePresenter mPresenter;

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
        mPresenter = new ProfilePresenter(this);

        initView();
    }

    public void initView() {
        updateProfileView();

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.profile_user_image:
                        startCropActivity();
                        break;

                    case R.id.profile_intro_write_btn:
                        showIntroDialog();
                        break;

                    case R.id.profile_tag_write_btn:
                        mTagLayout.submitTag();
                        mPresenter.changeProfileTagList(mTagLayout.getTags());
                        break;
                }
            }
        };

        mProfileImageView.setOnClickListener(mClickListener);
        mWriteIntroButton.setOnClickListener(mClickListener);
        mWriteTagButton.setOnClickListener(mClickListener);

        mMainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideVirtualKeyboard(view);
                return false;
            }
        });

        mTagLayout.setOnTagChangeListener(new TagGroup.OnTagChangeListener() {
            @Override
            public void onAppend(TagGroup tagGroup, String tag) {
                mPresenter.changeProfileTagList(tagGroup.getTags());
            }

            @Override
            public void onDelete(TagGroup tagGroup, String tag) {
                mPresenter.changeProfileTagList(tagGroup.getTags());
            }
        });
    }

    private void updateProfileView() {
        mPresenter.getPreviousProfileDatas();
    }

    public void inflateImageView(String imageUrl) {
        if (imageUrl == null) {
            mPresenter.changeProfileImage(PROFILE_DEFAULT_IMAGE);
        } else {
            ImageUtil.loadUrlImage(mProfileImageView, imageUrl);
        }
    }

    public void inflateIntroText(String introText) {
        if (introText != null) {
            mIntroContentText.setText(introText);
        }
    }

    public void inflateTagView(String[] tagList) {
        if (tagList != null) {
            mTagLayout.setTags(tagList);
        }
    }

    public void startCropActivity() {
        Intent intent = CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle("편집")
                .setCropShape(CropImageView.CropShape.OVAL)
                .setAspectRatio(1, 1)
                .getIntent(mContext);

        startActivityForResult(intent, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                mPresenter.changeProfileImage(result.getUri());
                inflateImageView(result.getUri().toString());
            }
        }
    }

    public void showIntroDialog() {
        final IntroDialog introDialog = new IntroDialog(getActivity(), mIntroContentText.getText().toString());
        introDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dia) {
                if (introDialog.getMode() == DIALOG_POSITIVE_MODE) {
                    mPresenter.changeProfileIntro(introDialog.getText());
                    inflateIntroText(introDialog.getText());
                }
            }
        });

        introDialog.show();
    }

    public void hideVirtualKeyboard(View view) {
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
