package org.collapsed.ssuparty_android.ui.partylist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.model.profile.ProfileData;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.createparty.CreatePartyActivity;
import org.collapsed.ssuparty_android.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.gujun.android.taggroup.TagGroup;

public class PartyListFragment extends BaseFragment implements PartyListContract.View {

    private static final int INDEX_HOME = 0;
    private static final int INDEX_MY_PARTY = 1;
    private static final int INDEX_ALL_PARTY = 2;

    @BindView(R.id.partylist_fab_btn)
    FloatingActionButton mAddPartyButton;
    @BindView(R.id.partylist_list)
    RecyclerView mRecyclerView;

    private PartyListPresenter mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PartyListAdapter mPartyAdapter;
    private ProfileListAdapter mProflieAdapter;
    private ArrayList<PartyData> mPartyDataList = new ArrayList<>();
    private ArrayList<ProfileData> mProfileDataList = new ArrayList<>();
    private Unbinder mUnbinder;

    public static PartyListFragment newInstance() {
        PartyListFragment fragment = new PartyListFragment();
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
        return inflater.inflate(R.layout.fragment_partylist, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mUnbinder = ButterKnife.bind(this, rootView);

        mPresenter = new PartyListPresenter(this);

        initView(rootView);
    }

    @Override
    public void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getActivity());

        mPartyAdapter = new PartyListAdapter();
        mProflieAdapter = new ProfileListAdapter();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPartyAdapter);


        mAddPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePartyActivity.class);
                getActivity().startActivityForResult(intent, CreatePartyActivity.CREATE_ACTIVITY_REQUEST_CODE);
            }
        });

        mAddPartyButton.setVisibility(View.GONE);
    }

    public void addPartyItemToList(PartyData partyData) {
        mPartyDataList.add(partyData);
        mPartyAdapter.notifyDataSetChanged();
    }

    public void addProfileItemToList(ProfileData profileData) {
        mProfileDataList.add(profileData);
        mProflieAdapter.notifyDataSetChanged();
    }

    public void inflateView(int index) {
        switch (index) {
            case INDEX_HOME:
                break;

            case INDEX_MY_PARTY:
                break;

            case INDEX_ALL_PARTY:
                showAddPartyButton();
                mRecyclerView.setAdapter(mPartyAdapter);
                break;

            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public void showAddPartyButton() {
        mAddPartyButton.setVisibility(View.VISIBLE);
    }

    class PartyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_party, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            PartyData item = mPartyDataList.get(position);

            ((CustomViewHolder) viewHolder).titleText.setText(item.getTitle());
            ((CustomViewHolder) viewHolder).categoryText.setText(item.getCategory());
            ((CustomViewHolder) viewHolder).memberText.setText("0/" + item.getMemberNum());

            List<String> tagList = item.getTags();

            if (tagList != null) {
                ((CustomViewHolder) viewHolder).tagList.setTags(tagList);
            }

            ((CustomViewHolder) viewHolder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            viewHolder.itemView.setTag(item);
        }

        @Override
        public int getItemCount() {
            return mPartyDataList.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {

            public TextView titleText, memberText, categoryText;
            public LinearLayout rootView;
            public TagGroup tagList;

            public CustomViewHolder(View view) {
                super(view);

                titleText = view.findViewById(R.id.party_item_title_txt);
                memberText = view.findViewById(R.id.party_item_member_num_txt);
                categoryText = view.findViewById(R.id.party_item_category_txt);
                tagList = view.findViewById(R.id.party_item_tag_layout);

                rootView = view.findViewById(R.id.party_item_root_layout);
            }
        }
    }

    class ProfileListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
            return new ProfileViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            ProfileData item = mProfileDataList.get(position);

            ImageUtil.loadUrlImage(((ProfileViewHolder)viewHolder).profileImgae, item.getProfileImageUri());
            ((ProfileViewHolder)viewHolder).nicknameText.setText(item.getNickname());
            ((ProfileViewHolder)viewHolder).majorText.setText(item.getMajor());
            ((ProfileViewHolder)viewHolder).gradeText.setText(item.getGrade());

            ((ProfileViewHolder)viewHolder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            viewHolder.itemView.setTag(item);
        }

        @Override
        public int getItemCount() {
            return mProfileDataList.size();
        }

        private class ProfileViewHolder extends RecyclerView.ViewHolder {

            public TextView nicknameText, gradeText, majorText;
            public ImageView profileImgae;
            public LinearLayout rootView;

            public ProfileViewHolder(View itemView) {
                super(itemView);

                nicknameText = itemView.findViewById(R.id.profile_item_nickname_txt);
                majorText = itemView.findViewById(R.id.profile_item_major_txt);
                gradeText = itemView.findViewById(R.id.profile_item_grade_txt);
                profileImgae = itemView.findViewById(R.id.profile_item_image);

                rootView = itemView.findViewById(R.id.profile_item_root_layout);
            }
        }
    }

}
