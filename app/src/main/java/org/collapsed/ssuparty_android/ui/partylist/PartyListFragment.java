package org.collapsed.ssuparty_android.ui.partylist;


import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.party.PartyData;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.createparty.CreatePartyActivity;

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

    @BindView(R.id.partylist_title_txt)
    TextView mTitleText;
    @BindView(R.id.partylist_fab_btn)
    FloatingActionButton mAddPartyButton;
    @BindView(R.id.partylist_list)
    RecyclerView mRecyclerView;

    private PartyListPresenter mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PartyListAdapter mPartyAdapter;
    private ArrayList<PartyData> mPartyDataList = new ArrayList<>();
    private Unbinder mUnbinder;
    private Context mContext;

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
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPartyAdapter);

        mAddPartyButton.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CreatePartyActivity.class);
            getActivity().startActivityForResult(intent, CreatePartyActivity.CREATE_ACTIVITY_REQUEST_CODE);
        });

        mAddPartyButton.setVisibility(View.GONE);
    }

    public void addPartyItemToList(PartyData partyData) {
        if (partyData != null) {
            mPartyDataList.add(partyData);
            mPartyAdapter.notifyDataSetChanged();
        }
    }

    public void inflateView(int index) {
        switch (index) {
            case INDEX_HOME:
                break;

            case INDEX_MY_PARTY:
                mTitleText.setText("내 모임 리스트");
                mPresenter.fetchMyPartyList();
                mRecyclerView.setAdapter(mPartyAdapter);
                break;

            case INDEX_ALL_PARTY:
                mTitleText.setText("전체 모임 리스트");
                mPresenter.fetchAllPartyList();
                showAddPartyButton();
                mRecyclerView.setAdapter(mPartyAdapter);
                break;

            default:
                break;
        }
    }

    public void clearList() {
        mPartyDataList.clear();
    }

    public void setNewPartyInfo(Intent intent) {
        mPresenter.addNewParty(intent);
    }

    public void showPartyDetail(Intent intent) {
        getActivity().startActivity(intent);
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
            ((CustomViewHolder) viewHolder).memberText.setText(item.getCurrentMemberNum()+"/" + item.getMaxMemberNum());

            List<String> tagList = item.getTags();

            if (tagList != null) {
                ((CustomViewHolder) viewHolder).tagList.setTags(tagList);
            }

            ((CustomViewHolder) viewHolder).tagList.setOnClickListener(view -> mPresenter.createPartyDetail(getActivity(), item));
            ((CustomViewHolder) viewHolder).rootView.setOnClickListener(view -> mPresenter.createPartyDetail(getActivity(), item));

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
}
