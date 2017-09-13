package org.collapsed.ssuparty_android.ui.partydetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

public class PartyDetailActivity extends AppCompatActivity {

    private static final String TITLE_KEY = "title";
    private static final String CATEGORY_KEY = "category";
    private static final String DEADLINE_KEY = "deadline";
    private static final String MEMBER_KEY = "memberNum";
    private static final String INFO_KEY = "info";
    private static final String TAG_KEY = "tag";
    private static final String PARTY_KEY = "key";

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

    String mTitleValue, mCategoryValue, mDeadlineValue, mInfoValue, mMemberNumValue, mKeyValue;
    List<String> mTagListValue;
    Intent mDetailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        ButterKnife.bind(this);

        mDetailIntent = getIntent();

        initView();
    }

    private void initView() {

        mTitleValue = mDetailIntent.getStringExtra(TITLE_KEY);
        mCategoryValue = mDetailIntent.getStringExtra(CATEGORY_KEY);
        mDeadlineValue = mDetailIntent.getStringExtra(DEADLINE_KEY);
        mInfoValue = mDetailIntent.getStringExtra(INFO_KEY);
        mMemberNumValue = mDetailIntent.getStringExtra(MEMBER_KEY);
        mKeyValue = mDetailIntent.getStringExtra(PARTY_KEY);
        mTagListValue = mDetailIntent.getStringArrayListExtra(TAG_KEY);

        mTitleText.setText(mTitleValue);
        mCategoryText.setText(mCategoryValue);
        mDeadlineText.setText(mDeadlineValue);
        mInfoText.setText(mInfoValue);
        mMemberNumText.setText("1 명 / "+mMemberNumValue+"명 ");
        mTagLayout.setTags(mTagListValue);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
