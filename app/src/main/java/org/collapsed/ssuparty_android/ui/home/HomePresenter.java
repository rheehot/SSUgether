package org.collapsed.ssuparty_android.ui.home;

import android.support.annotation.NonNull;

import org.collapsed.ssuparty_android.model.ExampleAdBanner;
import org.collapsed.ssuparty_android.network.ExampleAdBannerFetcher;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

class HomePresenter implements HomeContract.UserActionListener, HomeContract.OnDataFetchedListener {
    private static final String TAG = HomePresenter.class.getSimpleName();

    private HomeFragment mView;

    public HomePresenter(@NonNull HomeFragment view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public void fetchNetworkingExample() {
        ExampleAdBannerFetcher fetcher = new ExampleAdBannerFetcher(this);
        fetcher.executeFetch();
    }

    @Override
    public void onBannerDataFetched(String response) {
        parseResponseData(response);
    }

    @Override
    public void parseResponseData(String response) {
        JSONObject object;
        String banners = null;
        try {
            object = new JSONObject(response);
            banners = object.getString("banners");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<ExampleAdBanner> bannerDatas = ExampleAdBanner.fromJson(banners);
        mView.showBannersDataWithToast(bannerDatas);
    }
}
