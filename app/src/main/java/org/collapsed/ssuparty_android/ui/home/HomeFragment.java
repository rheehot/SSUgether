package org.collapsed.ssuparty_android.ui.home;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.model.ExampleAdBanner;
import org.collapsed.ssuparty_android.ui.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class HomeFragment extends BaseFragment implements HomeContract.View {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private HomePresenter mPresenter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mPresenter = new HomePresenter(this);

        initView(rootView);
    }

    @Override
    public void initView(View rootView) {
        Button fetchExampleBtn = rootView.findViewById(R.id.home_fetch_test_btn);
        fetchExampleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.fetchNetworkingExample();
            }
        });
    }

    public void showBannersDataWithToast(List<ExampleAdBanner> bannerDatas) {
        for (ExampleAdBanner banner : bannerDatas) {
            Toast.makeText(getActivity(), banner.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
