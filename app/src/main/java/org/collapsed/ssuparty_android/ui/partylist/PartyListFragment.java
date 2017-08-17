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

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.adapter.PartyListAdapter;
import org.collapsed.ssuparty_android.adapter.ProfileListAdapter;
import org.collapsed.ssuparty_android.model.PartyData;
import org.collapsed.ssuparty_android.model.ProfileData;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.createparty.CreatePartyActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PartyListFragment extends BaseFragment implements PartyListContract.View {

    private static final int INDEX_HOME = 0;
    private static final int INDEX_MY_PARTY = 1;
    private static final int INDEX_ALL_PARTY = 2;

    private static final int START_CREATE_ACTIVITY = 1;

    @BindView(R.id.partylist_fab_btn)
    FloatingActionButton mAddPartyButton;
    @BindView(R.id.partylist_recycler_view)
    RecyclerView mRecyclerView;

    private PartyListPresenter mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PartyListAdapter mPartyAdapter;
    private ProfileListAdapter mProflieAdapter;
    private ArrayList<PartyData> mPartyDataList;
    private ArrayList<ProfileData> mProfileDataList;
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
        mUnbinder = ButterKnife.bind(this,rootView);

        mPresenter = new PartyListPresenter(this);

        initView(rootView);
    }

    @Override
    public void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getActivity());

        mPartyDataList = new ArrayList<>();
        mProfileDataList = new ArrayList<>();
        mProfileDataList.add(new ProfileData(mPresenter.getUserProfileImageUrl(),"티모","숭실대","자바"));

        mPartyAdapter = new PartyListAdapter(mPartyDataList, getActivity());
        mProflieAdapter = new ProfileListAdapter(mProfileDataList, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPartyAdapter);

        mAddPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePartyActivity.class);
                startActivityForResult(intent, START_CREATE_ACTIVITY);
            }
        });

        mAddPartyButton.setVisibility(View.GONE);
    }

    public void addPartyItemToList(PartyData object) {
        mPresenter.setNewDataToAdapter(mPartyDataList, mPartyAdapter, object);
    }

    public void addProfileItemToList(ProfileData object) {
        mPresenter.setNewDataToAdapter(mProfileDataList, mProflieAdapter, object);
    }

    public void inflateView(int index) {
        switch (index) {
            case INDEX_HOME:
                break;

            case INDEX_MY_PARTY:
                showAddPartyButton();
                break;

            case INDEX_ALL_PARTY:
                showAddPartyButton();

                //profile data 테스트용 코드
                mRecyclerView.setAdapter(mProflieAdapter);

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

}
