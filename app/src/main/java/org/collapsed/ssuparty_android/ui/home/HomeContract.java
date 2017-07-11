package org.collapsed.ssuparty_android.ui.home;

import org.collapsed.ssuparty_android.model.ExampleAdBanner;

import java.util.List;

public interface HomeContract {
    interface View {
        void initView(android.view.View rootView);

        void showBannersDataWithToast(List<ExampleAdBanner> bannerDatas);
    }

    interface UserActionListener {
        void fetchNetworkingExample();

        void parseResponseData(String response);
    }

    interface OnDataFetchedListener {
        void onBannerDataFetched(String response);
    }
}
