package org.collapsed.ssuparty_android.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.collapsed.ssuparty_android.GlobalApplication;
import org.collapsed.ssuparty_android.AppConfig;
import org.collapsed.ssuparty_android.ui.home.HomeContract;

public class ExampleAdBannerFetcher {
    private static final String TAG = ExampleAdBannerFetcher.class.getSimpleName();

    private RequestQueue mQueue;
    private HomeContract.OnDataFetchedListener mListener;

    public ExampleAdBannerFetcher(HomeContract.OnDataFetchedListener listener) {
        mQueue = GlobalApplication.getRequestQueue();
        mListener = listener;
    }

    public void executeFetch() {
        StringRequest request = new StringRequest(Request.Method.GET, AppConfig.BASE_BANNER_ADDRESS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mListener.onBannerDataFetched(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());
            }
        });
        mQueue.add(request);
    }
}
