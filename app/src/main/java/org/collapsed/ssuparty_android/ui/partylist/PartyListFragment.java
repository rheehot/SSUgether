package org.collapsed.ssuparty_android.ui.commomlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.adapter.PartyListAdapter;
import org.collapsed.ssuparty_android.model.NewPartyInfo;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.createparty.CreatePartyActivity;

import java.util.ArrayList;

public class CommonListFragment extends BaseFragment implements CommonListContract.View{

    private static final int INDEX_HOME = 0;
    private static final int INDEX_MY_PARTY = 1;
    private static final int INDEX_ALL_PARTY = 2;

    private CommonListPresenter mPresenter;

    private FloatingActionButton mAddPartyButton;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private PartyListAdapter mListAdapter;
    private ArrayList<NewPartyInfo> mListData;

    public static CommonListFragment newInstance() {
        CommonListFragment fragment = new CommonListFragment();
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
        return inflater.inflate(R.layout.fragment_common, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mPresenter = new CommonListPresenter(this);

        initView(rootView);
    }

    @Override
    public void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getActivity());

        mListData = new ArrayList<NewPartyInfo>();

        mListData.add(new NewPartyInfo("MY","5","공모전","D-1","ㅇㅇ",null));
        mListData.add(new NewPartyInfo("MY","5","공모전","D-1","ㅇㅇ",null));

        mListAdapter = new PartyListAdapter(mListData,getActivity());

        mRecyclerView = rootView.findViewById(R.id.common_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mListAdapter);

        mAddPartyButton = rootView.findViewById(R.id.common_btn_fab);
        mAddPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePartyActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        mAddPartyButton.setVisibility(View.GONE);
    }

    /*public void addPartyItemToList(int index, NewPartyInfo object){
        switch (index){
            case INDEX_HOME:
                mListData.add(object);
                break;

            case INDEX_MY_PARTY:
                mMyPartyListData.add(object);
                mMyPartyAdapter.addItem(mMyPartyListData);
                break;

            case INDEX_ALL_PARTY:
                mFindPartyListData.add(object);
                mFindPartyAdapter.addItem(mFindPartyListData);
                break;

            default:
                break;
        }
    }
*/
    public void showViewInList(int index){
        switch (index){
            case INDEX_HOME:
                break;

            case INDEX_MY_PARTY:
                break;

            case INDEX_ALL_PARTY:
                showAddPartyButton();
                break;

            default:
                break;
        }
    }
    public void showAddPartyButton(){
        mAddPartyButton.setVisibility(View.VISIBLE);
    }
}
