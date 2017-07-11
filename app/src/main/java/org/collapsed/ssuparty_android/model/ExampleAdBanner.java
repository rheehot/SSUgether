package org.collapsed.ssuparty_android.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ExampleAdBanner {
    private String img_url;
    private String href;
    private String name;

    public String getImg_url() {
        return img_url;
    }

    public String getHref() {
        return href;
    }

    public String getName() {
        return name;
    }

    public static List<ExampleAdBanner> fromJson(String response) {
        Type listType = new TypeToken<List<ExampleAdBanner>>() {
        }.getType();
        List<ExampleAdBanner> bannerList = new Gson().fromJson(response, listType);
        return bannerList;
    }
}
