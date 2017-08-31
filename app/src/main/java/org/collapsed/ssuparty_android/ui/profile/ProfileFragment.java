package org.collapsed.ssuparty_android.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.event.BusProvider;
import org.collapsed.ssuparty_android.event.profile.FirstProfileEvent;
import org.collapsed.ssuparty_android.event.profile.IntroEvent;
import org.collapsed.ssuparty_android.event.profile.TagEvent;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.customview.CustomDialog;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment {

    private static final int DIALOG_POSITIVE_MODE = 2;

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
    TagContainerLayout mTagLayout;

    private Unbinder mUnbinder;
    private Context mContext;
    private Uri imageUri;
    private View.OnClickListener mClickListener;
    private String mContentValue;

    private ProfilePresenter mPresenter;
    private Bus mEventBus = BusProvider.getInstance();

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
        mEventBus.register(this);

        mUnbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        mPresenter = new ProfilePresenter(this);

        initView();
    }

    public void initView() {
        //mTagLayout.addTag("태그를 등록해주세요!");
        inflateProfileImage();

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.profile_user_image:
                        Intent intent = CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                                .setActivityTitle("편집")
                                .setCropShape(CropImageView.CropShape.OVAL)
                                .setAspectRatio(1, 1)
                                .getIntent(mContext);

                        startActivityForResult(intent, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
                        break;

                    case R.id.profile_intro_write_btn:
                        final CustomDialog introDialog = new CustomDialog(getActivity());
                        introDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dia) {
                                if (introDialog.getMode() == DIALOG_POSITIVE_MODE) {
                                    if (!introDialog.getText().equals("")) {
                                        mPresenter.onChangedIntroduction(introDialog.getText());
                                    }
                                }
                            }
                        });

                        introDialog.show();
                        break;

                    case R.id.profile_tag_write_btn:
                        final CustomDialog tagDialog = new CustomDialog(getActivity());
                        tagDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dia) {
                                if (tagDialog.getMode() == DIALOG_POSITIVE_MODE) {
                                    mTagLayout.addTag(tagDialog.getText());
                                    mPresenter.onChangedTags(mTagLayout.getTags());
                                }
                            }
                        });

                        tagDialog.show();
                        break;
                }
            }
        };

        mProfileImageView.setOnClickListener(mClickListener);
        mWriteIntroButton.setOnClickListener(mClickListener);
        mWriteTagButton.setOnClickListener(mClickListener);

        mTagLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(final int position, String text) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("해당 내용을 삭제하시겠습니까?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTagLayout.removeTag(position);
                    }
                });
                dialog.show();
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
    }

    public void inflateProfileImage() {
        mPresenter.loadImageUrlFromFirebase();
    }

    //called by ProfileDB
    @Subscribe
    public void setNewTags(TagEvent tagEvent) {
        mTagLayout.setTags(tagEvent.getTags());
    }

    //called by ProfileDB
    @Subscribe
    public void setNewIntroduction(IntroEvent introEvent) {
        mIntroContentText.setText(introEvent.getIntroduction());
    }

    //called by ProfileDB
    @Subscribe
    public void inflateProfileImageInFirstTime(FirstProfileEvent imageEvent) {
        ImageUtil.loadUrlImage(mProfileImageView, imageEvent.getUrl(), null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                mPresenter.onChangedProfileImage(result.getUri());
                mProfileImageView.setImageURI(result.getUri());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
