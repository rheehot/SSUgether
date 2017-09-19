package org.collapsed.ssuparty_android.ui.partydetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

public class PartyDetailActivity extends AppCompatActivity {

    @BindView(R.id.party_detail_main_img)
    ImageView mMainImageView;
    @BindView(R.id.party_detail_title_txt)
    TextView mTitleText;
    @BindView(R.id.party_detail_category_txt)
    TextView mCategoryText;
    @BindView(R.id.party_detail_deadline_txt)
    TextView mDeadlineText;
    @BindView(R.id.party_detail_info_txt)
    TextView mInfoText;
    @BindView(R.id.party_detail_member_state_txt)
    TextView mMemberNumText;
    @BindView(R.id.party_detail_tag_layout)
    TagGroup mTagLayout;
    @BindView(R.id.party_detail_cancel_btn)
    ImageButton mCancelButton;

    private PartyData mPartyData;
    private PartyDetailPresenter mPresenter;
    private Context mContext;
    private Uri imageUri;
    private String mFounderId, mMyId, mPartyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);

        ButterKnife.bind(this);
        mPresenter = new PartyDetailPresenter(this);
        mPartyData = (PartyData) getIntent().getSerializableExtra("PartyData");
        mContext = getApplicationContext();

        initIDs();
        initView();
    }

    private void initView() {

        mPresenter.getPreviousPartyImage(mPartyId);
        mTitleText.setText(mPartyData.getTitle());
        mCategoryText.setText(mPartyData.getCategory());
        mDeadlineText.setText(mPartyData.getRecruitDate());
        mInfoText.setText(mPartyData.getDescription());
        mMemberNumText.setText(mPartyData.getCurrentMemberNum() + " / " + mPartyData.getMaxMemberNum() + "명 ");

        if (mPartyData.getTags() != null) {
            mTagLayout.setTags(mPartyData.getTags());
        }

        mMainImageView.setOnClickListener(view -> startCropActivity());

        mCancelButton.setOnClickListener(view -> finish());
    }

    public void startCropActivity() {
        if (mFounderId.equals(mMyId)) {
            Intent intent = CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("편집")
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setAspectRatio(1, 1)
                    .getIntent(mContext);

            startActivityForResult(intent, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        }
    }

    public void inflateImageView(String imageUrl) {
            ImageUtil.loadUrlImage(mMainImageView, imageUrl);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                Uri imageUrl = result.getUri();
                mPresenter.changePartyImage(mPartyId, imageUrl);
                inflateImageView(imageUrl.toString());
            }
        }
    }

    public void initIDs() {
        mFounderId = mPartyData.getFounder();
        mMyId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mPartyId = mPartyData.getPartyID();
    }
}
