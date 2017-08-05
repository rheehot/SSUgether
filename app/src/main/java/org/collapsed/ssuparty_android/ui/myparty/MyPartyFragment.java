package org.collapsed.ssuparty_android.ui.myparty;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.adapter.RVAdapter;
import org.collapsed.ssuparty_android.model.NewPartyInfo;
import org.collapsed.ssuparty_android.ui.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyPartyFragment extends BaseFragment implements MyPartyContract.View {
    private static final String TAG = MyPartyFragment.class.getSimpleName();

    private MypartyPresenter mPresenter;

    private RecyclerView mCreatedPartyList, mJoinedPartyList;

    private LinearLayoutManager mTopLayoutManager, mBottomLayoutManager;

    private RVAdapter mPartyListAdapter;

    private ArrayList<NewPartyInfo> mDataSet;

    public static MyPartyFragment newInstance() {
        MyPartyFragment fragment = new MyPartyFragment();
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
        return inflater.inflate(R.layout.fragment_myparty, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mPresenter = new MypartyPresenter(this);

        initView(rootView);
    }

    public void initView(View rootView){

        mTopLayoutManager = new LinearLayoutManager(getActivity());
        mBottomLayoutManager = new LinearLayoutManager(getActivity());
        mPartyListAdapter = new RVAdapter(mDataSet, getActivity());

        List<String> dummyTag = new ArrayList<String>(){
            {
                add("UX");
                add("UI");
                add("JAVA");
            }
        };

        NewPartyInfo dummyClass = new NewPartyInfo("소공전 버스기사 구합니다","3/10","공모전","D - 37","모임소개입니다.",dummyTag);

        mDataSet = new ArrayList<>();
        mDataSet.add(dummyClass);
        mDataSet.add(dummyClass);
        mDataSet.add(dummyClass);
        mDataSet.add(dummyClass);
        mDataSet.add(dummyClass);
        mDataSet.add(dummyClass);
        mDataSet.add(dummyClass);
        mDataSet.add(dummyClass);
        mPartyListAdapter.addItem(mDataSet);
        mPartyListAdapter.notifyDataSetChanged();

        mCreatedPartyList = rootView.findViewById(R.id.myparty_created_party_list);
        mCreatedPartyList.setHasFixedSize(true);
        mCreatedPartyList.setLayoutManager(mTopLayoutManager);
        mCreatedPartyList.scrollToPosition(0);
        mCreatedPartyList.setAdapter(mPartyListAdapter);
        mCreatedPartyList.setItemAnimator(new DefaultItemAnimator());

        mCreatedPartyList = rootView.findViewById(R.id.myparty_joined_party_list);
        mCreatedPartyList.setHasFixedSize(true);
        mCreatedPartyList.setLayoutManager(mBottomLayoutManager);
        mCreatedPartyList.scrollToPosition(0);
        mCreatedPartyList.setAdapter(mPartyListAdapter);
        mCreatedPartyList.setItemAnimator(new DefaultItemAnimator());
    }

    public void addMyParty(NewPartyInfo object){
        mDataSet.add(object);
        mPartyListAdapter.addItem(mDataSet);
    }
}
