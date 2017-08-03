package org.collapsed.ssuparty_android.ui.findparty;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.adapter.RVAdapter;
import org.collapsed.ssuparty_android.model.NewPartyInfo;
import org.collapsed.ssuparty_android.ui.BaseFragment;
import org.collapsed.ssuparty_android.ui.createparty.CreatePartyActivity;

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

import java.util.ArrayList;


public class FindPartyFragment extends BaseFragment implements FindPartyContract.View {
    private static final String TAG = FindPartyFragment.class.getSimpleName();

    private FindPartyPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<NewPartyInfo> mDataSet;

    private FloatingActionButton mFab;

    public static FindPartyFragment newInstance() {
        FindPartyFragment fragment = new FindPartyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_findparty, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mPresenter = new FindPartyPresenter(this);

        initView(rootView);
    }

    private void initData() {
        mDataSet = new ArrayList<>();
    }

    @Override
    public void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.findparty_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mFab = rootView.findViewById(R.id.findparty_btn_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePartyActivity.class);
                startActivity(intent);
            }
        });

        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RVAdapter(mDataSet, getActivity());
        mAdapter.notifyDataSetChanged();
    }

    public void addNewParty(String title, String member) {
        mAdapter.addItem(mDataSet);
        mAdapter.notifyDataSetChanged();
    }
}
