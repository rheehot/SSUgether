package org.collapsed.ssuparty_android.ui.findparty;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.adapter.RVAdapter;
import org.collapsed.ssuparty_android.model.Party;
import org.collapsed.ssuparty_android.ui.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class FindPartyFragment extends BaseFragment implements FindPartyContract.View {
    private static final String TAG = FindPartyFragment.class.getSimpleName();

    private FindPartyPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Party> mDataSet;


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
        View view = inflater.inflate(R.layout.fragment_findparty, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mPresenter = new FindPartyPresenter(this);
        initView(rootView);

    }
    private void initData(){
mDataSet = new ArrayList<Party>();
        mDataSet.add(new Party(R.drawable.default_image,"공모전 파티 구함","4/5"));
        mDataSet.add(new Party(R.drawable.default_image,"안드로이드 스터디 하실분","1/3"));
        mDataSet.add(new Party(R.drawable.default_image,"점심 먹을 사람1","1/4"));
        mDataSet.add(new Party(R.drawable.default_image,"점심 먹을 사람2","1/4"));
        mDataSet.add(new Party(R.drawable.default_image,"점심 먹을 사람3","1/4"));
        mDataSet.add(new Party(R.drawable.default_image,"점심 먹을 사람4","1/4"));
        mDataSet.add(new Party(R.drawable.default_image,"점심 먹을 사람5","1/4"));
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RVAdapter(mDataSet,getActivity().getApplicationContext());

    }

    @Override
    public void initView(View rootView) {
        Button createPartyBtn = rootView.findViewById(R.id.create_party_btn);
        createPartyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getPartyInfo(getActivity().getApplicationContext());
            }
        });
    }

    public void newParty(String title, String member){
        mDataSet.add(new Party(R.drawable.default_image,title,"0/"+member));
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }


}
