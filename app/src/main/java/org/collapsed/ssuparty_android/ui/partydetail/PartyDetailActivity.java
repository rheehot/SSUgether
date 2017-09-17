package org.collapsed.ssuparty_android.ui.partydetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.party.PartyData;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

public class PartyDetailActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        ButterKnife.bind(this);
        mPartyData = (PartyData) getIntent().getSerializableExtra("PartyData");

        initView();
    }

    private void initView() {
        mTitleText.setText(mPartyData.getTitle());
        mCategoryText.setText(mPartyData.getCategory());
        mDeadlineText.setText(mPartyData.getRecruitDate());
        mInfoText.setText(mPartyData.getDescription());
        mMemberNumText.setText(mPartyData.getCurrentMemberNum() + " / " + mPartyData.getMaxMumberNum() + "명 ");
        mTagLayout.setTags(mPartyData.getTags());

        mCancelButton.setOnClickListener(view -> finish());
    }
}
