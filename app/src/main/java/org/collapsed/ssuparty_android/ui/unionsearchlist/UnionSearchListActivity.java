package org.collapsed.ssuparty_android.ui.unionsearchlist;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.databinding.UnionSearchListActivityBinding;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.userinfo.UserInfoData;
import org.collapsed.ssuparty_android.ui.customview.CircleImageView;
import org.collapsed.ssuparty_android.ui.home.HomeFragment;
import org.collapsed.ssuparty_android.ui.partydetail.PartyDetailActivity;
import org.collapsed.ssuparty_android.ui.userprofiledetail.UserProfileDetailActivity;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

public class UnionSearchListActivity extends AppCompatActivity {

    private UnionSearchListActivityBinding mBinding;
    private ListView mListView;
    private TextView mToolbar;
    private ImageButton mBackButton;
    private int mListResultCategory;
    private ArrayList<UserInfoData> mProfiles;
    private ArrayList<PartyData> mParties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.union_search_list_activity);
        mBackButton = mBinding.unionsearchBackBtn;

        mToolbar = mBinding.unionsearchTitle;
        mListResultCategory = getIntent().getIntExtra("ListResultCategory", 0);
        if (mListResultCategory == 0) {
            mToolbar.setText("전체 프로필 검색결과");
            mProfiles = (ArrayList<UserInfoData>) getIntent().getSerializableExtra("Items");

            if(mProfiles.size() > 0) {
                mListView = mBinding.unionsearchList;
                mListView.setAdapter(new AllProfileSearchAdapter(this, mProfiles));
                mListView.setOnItemClickListener((adapterView, view, i, l) -> {
                    Intent intent = new Intent(this, UserProfileDetailActivity.class);
                    intent.putExtra("UserInfo", (UserInfoData) adapterView.getItemAtPosition(i));
                    startActivity(intent);
                });
            } else {
                mBinding.unionsearchPlaceLayout.setVisibility(View.VISIBLE);
            }

        } else {
            mToolbar.setText("전체 모임 검색결과");
            mParties = (ArrayList<PartyData>) getIntent().getSerializableExtra("Items");

            if (mParties.size() > 0) {
                mListView = mBinding.unionsearchList;
                mListView.setAdapter(new AllPartySearchAdapter(this, mParties));
                mListView.setOnItemClickListener((adapterView, view, i, l) -> {
                    Intent intent = new Intent(this, PartyDetailActivity.class);
                    intent.putExtra("PartyData", (PartyData) adapterView.getItemAtPosition(i));
                    startActivity(intent);
                });
            } else {
                mBinding.unionsearchPlaceLayout.setVisibility(View.VISIBLE);
            }
        }

        mBackButton.setOnClickListener(view -> finish());
    }

    private class AllProfileSearchAdapter extends BaseAdapter {
        private final ArrayList<UserInfoData> mUserInfos;
        private LayoutInflater mInflater;
        private ViewHolder viewHolder;

        public AllProfileSearchAdapter(Context context, ArrayList<UserInfoData> profiles) {
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
                viewHolder.profileIntroduce.setText("소개 : " + userData.getIntro());
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

    private class AllPartySearchAdapter extends BaseAdapter {
        private final ArrayList<PartyData> mParties;
        private LayoutInflater mInflater;
        private ViewHolder viewHolder;

        public AllPartySearchAdapter(Context context, ArrayList<PartyData> parties) {
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

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = convertView;

            if (view == null) {
                viewHolder = new ViewHolder();
                view = mInflater.inflate(R.layout.item_party, null);
                viewHolder.titleText = view.findViewById(R.id.party_item_title_txt);
                viewHolder.memberText = view.findViewById(R.id.party_item_member_num_txt);
                viewHolder.categoryText = view.findViewById(R.id.party_item_category_txt);
                viewHolder.tagList = view.findViewById(R.id.party_item_tag_layout);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.titleText.setText(mParties.get(i).getTitle());
            viewHolder.memberText.setText(mParties.get(i).getCurrentMemberNum() + "/" + mParties.get(i).getMaxMemberNum());
            viewHolder.categoryText.setText(mParties.get(i).getCategory());

            if (mParties.get(i).getTags() != null)
                viewHolder.tagList.setTags(mParties.get(i).getTags());

            return view;
        }

        class ViewHolder {
            public TextView titleText, memberText, categoryText;
            public TagGroup tagList;
        }
    }

}
